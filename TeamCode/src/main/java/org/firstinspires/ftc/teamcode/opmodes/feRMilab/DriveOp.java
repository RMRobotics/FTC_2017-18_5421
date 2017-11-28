package org.firstinspires.ftc.teamcode.opmodes.feRMilab;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.BACKWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by tina on 11/16/17.
 */

@Autonomous(name = "RED: Auto")
public class DriveOp extends GeRMLinear{

    @Override
    public void runOpMode() {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);
//          Jewel
//        Drive forward to align arm
        driveStop(ENCODER, 1200, 0.5);
//        Turn servo to let down jewel arm
        jewelArm.setPosition(-0.93);
//        Detect jewel color
        boolean detected = false;
        double initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 200 && opModeIsActive()) {
            if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 10) <= 1) {
                //0x04 is color number
                //10 is red
                left = Color.RED;
                right = Color.BLUE;
            } else if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 3) <= 1) {
                //3 is blue
                left = Color.BLUE;
                right = Color.RED;
            } else {
                left = Color.NEITHER;
            }

            // determine side with correct color
            if (left == Color.RED && right == Color.BLUE) {
                detected = true;
                jewel = RIGHT;
                telemetry.addData("SURE", jewel.toString());
            } else if (left == Color.BLUE && right == Color.RED) {
                detected = true;
                jewel = LEFT;
                telemetry.addData("SURE", jewel.toString());
            } else {
                if (left == Color.RED || right == Color.BLUE) {
                    detected = true;
                    jewel = RIGHT;
                } else if (left == Color.BLUE || right == Color.RED) {
                    detected = true;
                    jewel = LEFT;
                }
                // output probable side with correct color
                telemetry.addData("UNSURE:", jewel.toString());
            }
            if (detected == true) {
                jewelArm.setPosition(0.8);
            }
            telemetry.update();
        }
//        Scan pictograph using Vuforia; store position
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
//        Turn robot depending on jewel color
        switch (jewel){
            case LEFT:{ //largest amount
                turn(CENTER, 12, .4);
                break;
            }
            case RIGHT: { //smallest amount
                turn(CENTER, -12, .4);
                break;
            }
        }
//          Glyphs 45
//        Turn 90 towards cryptoboxes
        turn(CENTER, 90, .4);
//        Drive distance according to pictograph position (use predetermined distances or vuforia to detect three column
        switch (vuMark){
            case LEFT:{ //largest amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
            case RIGHT: { //smallest amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
            case CENTER: { //middle amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
            case UNKNOWN:{ //middle amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
        }
//        Turn 90 to face boxes
        turn(CENTER, 90, .4);
//        Turn compression wheels to push glyph into correct column
        initTime = runtime.milliseconds();
        glyphGrabber.setPower(0.8);
        sleep(200);
        glyphGrabber.setPower(0);
//          Park
//        Drive forward to park in safety triangle
        driveStop(ENCODER, 1200, 0.5);
    }
}

