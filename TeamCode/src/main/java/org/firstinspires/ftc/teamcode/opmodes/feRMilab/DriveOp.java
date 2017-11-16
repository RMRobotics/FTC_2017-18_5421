package org.firstinspires.ftc.teamcode.opmodes.feRMilab;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.core.FeRMiLinear;

/**
 * Created by tina on 11/16/17.
 */

public class DriveOp extends FeRMiLinear{
    public static final String TAG = "RED1 Auto";
    private DcMotor BR;
    private DcMotor BL;
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor glyphGrabber;
    private Servo jewel;
    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    public void driveEncoder(int value, double power) {
        double mag = Math.abs(power);
        value = value * scale;
        double dir = Math.signum(value - FL.getCurrentPosition());
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                setEnc(val);
        int shift = 0;
        // TODO: check to see if acceleration code functions properly
        while (Math.abs(FL.getCurrentPosition() - value) > 5 && opModeIsActive()) {
            telemetry.addData("current Encoder value: ", FL.getCurrentPosition());
            telemetry.update();
            if (shift * 0.02 < mag) {
                setDrive(scale * dir * shift * 0.05);
                shift++;
                sleep(200);
            } else {
                setDrive(scale * dir * mag);
            }
        }
    }

    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "AY77tqP/////AAAAGfLr0EwiUEvBgqYkqzIkmW1s7GIs/g3aXlDXMXvvOAN8V1hF4ZLx8qOibfX//3q6tSGlobO4cnOU27ue2pwMeg5Z10jgtWm2S01GM1FcFYr1LFSl/MGT/2KJ+zTv0051h3MvcY8/o9pKTGsTuBA9gJ1Cfm48BLNp8kbftffjMPpuCQZapAstwIF5KsZZ2WY6JDdUNiJfU6YcML5Q+DSRM+wF8zf5iiKavSG2WW6jP1f8RukTPjFGdRJsoz05ktSJ/xi6sKh+vTlLU92K7yO38pwJ3nfPOQJrtoE8OBgzRLMvWz9UwaswWps0NJPyr8iOTGsixtWO35lZjUzP5hDkNLhzl1DFRLJUQPnltmhBif5c";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        //relicTemplate.setName("relicVuMarkTemplate");

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {
            driveEncoder(200, 0.5);
            liftHold.setPosition(-0.93);
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            while (vuMark == RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "not visible");
                vuMark = RelicRecoveryVuMark.from(relicTemplate);
            }
            telemetry.addData("VuMark", "%s visible", vuMark);

            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
            //telemetry.addData("Pose", format(pose));
                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
//                    double tX = trans.get(0);
//                    double tY = trans.get(1);
//                    double tZ = trans.get(2);
                int tX = (int) trans.get(0);
                int tY = (int) trans.get(1);
                int tZ = (int) trans.get(2);
                telemetry.addData("Trans", tX + ", " + tY + ", " + tZ);
                // X is side to side
                // Y is up and down
                // Z is towards and away, normal distance to pictograph

                // Extract the rotational components of the target relative to the robot
//                    double rX = rot.firstAngle;
//                    double rY = rot.secondAngle;
//                    double rZ = rot.thirdAngle;
                int rX = (int) rot.firstAngle;
                int rY = (int) rot.secondAngle;
                int rZ = (int) rot.thirdAngle;
                telemetry.addData("Rot", rX + ", " + rY + ", " + rZ);
            }
            telemetry.update();

        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
