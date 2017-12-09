package org.firstinspires.ftc.teamcode.opmodes.geRM.autoblue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;

/**
 * Created by tina on 11/16/17.
 */

@Autonomous(name = "BLUE: Auto")
public class BLUEAuto1 extends GeRMLinear{

    @Override
    public void runOpMode() {
        super.initialize(Color.BLUE, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);
//          Jewel
        //raise lift
        setLiftPower(200, .1);
//        Drive forward to align arm
        driveStop(ENCODER, 1200, 0.5);
//        Turn servo to let down jewel arm
        jewelArm.setPosition(-0.93);

//        Detect jewel color
        boolean detected = false;
        double initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 200 && opModeIsActive()) {
            if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 10) <= 1) {
                //0x04 is color number, 10 is red
                jewel = RIGHT;
                detected = true;
            } else if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 3) <= 1) {
                //3 is blue
                jewel = LEFT;
                detected = true;
            } else {
                detected = false;
            }
            telemetry.addData(Boolean.toString(detected), jewel.toString());

            telemetry.update();
        }
//        Scan pictograph using Vuforia; store position
        relicTrackables.activate();
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
        jewelArm.setPosition(0);
        turn(CENTER, 0, .4);
//          Glyphs 45
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
        turn(CENTER, -90, .4);
//        Drive closer to boxes
        driveStop(ENCODER, 500, 0.5);
        //lower lift
        setLiftPower(0,0.1);
//        Turn compression wheels to push glyph into correct column
        glyphGrabber.setPower(0.8);
        sleep(200);
        glyphGrabber.setPower(0);
//          Park
//        Drive forward to park in safety triangle
        driveStop(ENCODER, 1200, 0.5);
    }
}