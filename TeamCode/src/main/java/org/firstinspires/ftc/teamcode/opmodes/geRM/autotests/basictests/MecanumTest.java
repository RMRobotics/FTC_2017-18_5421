package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.basictests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Angela on 3/4/2018.
 */
@Disabled
public class MecanumTest {

    @Autonomous(name = "MecanumDrive", group = "Mecanum")
    public class autoMark3 extends LinearOpMode {


        private ColorSensor colorSensor;
        private DcMotor wheelFL;
        private DcMotor wheelFR;
        private DcMotor wheelBL;
        private DcMotor wheelBR;
        private DcMotor lift;
        //    private DcMotor arm;
        private Servo clawBL;
        private Servo clawBR;
        private Servo clawTL;
        private Servo clawTR;
        //    private Servo armT;
        private Servo gemBar;
//    private CRServo armB;

        private ElapsedTime time = new ElapsedTime();
        public static final String TAG = "Auto Version 1";
        /*    OpenGLMatrix lastLocation = null;*/
        VuforiaLocalizer vuforia;

        @Override
        public void runOpMode() throws InterruptedException{

            //declarations
//            colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");
            wheelFL = hardwareMap.dcMotor.get("wheelFL");
            wheelFR = hardwareMap.dcMotor.get("wheelFR");
            wheelBL = hardwareMap.dcMotor.get("wheelBL");
            wheelBR = hardwareMap.dcMotor.get("wheelBR");
//            lift = hardwareMap.dcMotor.get("lift");
//            clawBL = hardwareMap.servo.get("clawBL");
//            clawBR = hardwareMap.servo.get("clawBR");
//            clawTL = hardwareMap.servo.get("clawTL");
//            clawTR = hardwareMap.servo.get("clawTR");
//            gemBar = hardwareMap.servo.get("gemBar");
//            double /*timeToStance, timeToColumn,*/ rotate90; //rotate90 is the amount of time that it takes to rotate 90 degrees
//
//            //init values
//            gemBar.setDirection(Servo.Direction.REVERSE);
            wheelFL.setDirection(DcMotorSimple.Direction.REVERSE);
            wheelBL.setDirection(DcMotorSimple.Direction.REVERSE);
//            lift.setDirection(DcMotorSimple.Direction.REVERSE);
//            lift.setPower(0);
//            clawBL.setPosition(-0.7);
//            clawBR.setPosition(1);
//            rotate90 = 0.4;//0.82
//            colorSensor.enableLed(true);
//        arm = hardwareMap.dcMotor.get("arm");
//        armT = hardwareMap.servo.get("armT");
//        armB = hardwareMap.crservo.get("armB");
//        armB.setDirection(CRServo.Direction.FORWARD);
//            clawTR.setPosition(1);
//            clawTL.setPosition(-1);
//        armT.setPosition(0.5);
//        armB.setPower(0);
//        gemBar.setPosition(0);
//        timeToStance = 2;
//        timeToColumn = 0.75;


            //Activate Vuforia
//            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//            parameters.vuforiaLicenseKey = "AckoWtn/////AAAAGan7WAnq/0UVmQZG3sp7smBgRCNBnU1p+HmsTrC+W9TyxqaMlhFirDXglelvJCX4yBiO8oou6n7UWBfdRFbKHDqz0NIo5VcNHyhelmm0yK0vGKxoU0NZbQzjh5qVWnI/HRoFjM3JOq/LB/FTXgCcEaNGhXAqnz7nalixMeP8oRQlgX5nRVX4uE6w0K4yqIc5/FIDh1tn7PldiflmvNPhOW6FukPQD3d02wEnZB/JEchSSBzDbFA10XSgtYzXiweQI5tj+D5llLRrLh0mcWeouv55oSmya5RxUC26uEuO7bCAwyolWIuUr2Wh5oAG483nTD4vFhdjVMT7f0ovLO73C6xr2AXpNwen9IExRxBeosQ4";
//            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
//            this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
//            VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
//            VuforiaTrackable relicTemplate = relicTrackables.get(0);
//            relicTemplate.setName("relicVuMarkTemplate");
//            telemetry.update();
//            waitForStart();
//            relicTrackables.activate();
            waitForStart();

            while (opModeIsActive()) {
                move(2, 0.4, 0, 0);
                move2(2, 0.4, 45, 0);
            }
        }

        public void move(double duration, double power, double angle, double rotate) {
            angle *= (Math.PI / 180);
            time.reset();
            while (time.seconds() < duration) {
                wheelFL.setPower(power * Math.sin(angle + (Math.PI / 4)) + rotate);
                wheelFR.setPower(power * Math.cos(angle + (Math.PI / 4)) - rotate);
                wheelBL.setPower(power * Math.cos(angle + (Math.PI / 4)) + rotate);
                wheelBR.setPower(power * Math.sin(angle + (Math.PI / 4)) - rotate);
            }
        }

        public void move2(double duration, double power, double angle, double rotate) {
            angle *= (Math.PI / 180);
            time.reset();
            while (time.seconds() < duration) {
                wheelFL.setPower(power * Math.sin(angle + (Math.PI / 4)) + rotate);
                wheelBR.setPower(power * Math.sin(angle + (Math.PI / 4)) - rotate);
            }
        }
    }
}
