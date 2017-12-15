package org.firstinspires.ftc.teamcode.opmodes.geRM;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Direction;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;

/**
 * Created by poofs on 12/14/2017.
 */

public abstract class GeRMAuto extends GeRMLinear {
    protected double jewelPos = 1;
    protected int colorTime = 200;
    protected int turnVal1 = 12;

    protected int ejectTime = 200;

//    protected RelicRecoveryVuMark vuMark;

    protected void jewelAndVuf(int turnScale) {
        setLift(200, .1);
//        Drive forward to align arm
        driveStop(ENCODER, 1200, 0.5);
//        Turn servo to let down jewel arm
        jewelArm.setPosition(jewelPos);
//        Detect jewel color
        boolean detected;
        double initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < colorTime && opModeIsActive()) {
            try {
                if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 10) <= 1) {
                    //0x04 is color number, 10 is red
                    jewel = LEFT;
                    detected = true;
                } else if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 3) <= 1) {
                    //3 is blue
                    jewel = RIGHT;
                    detected = true;
                } else {
                    detected = false;
                }
                telemetry.addData("detected " + Boolean.toString(detected), jewel.toString());
                telemetry.update();
            } catch (NullPointerException e) {
                telemetry.addData("Null pointer- jewel detection", "");
            }

//        Scan pictograph using Vuforia; store position
//        relicTrackables.activate();
//        vuMark = RelicRecoveryVuMark.from(relicTemplate);
//        Turn robot depending on jewel color
            switch (jewel) {
                case LEFT: {
                    turn(CENTER, turnScale * turnVal1, .4);
                    break;
                }
                case RIGHT: {
                    turn(CENTER, -1 * turnScale * turnVal1, .4);
                    break;
                }
            }
            jewelArm.setPosition(.2);
        }
    }

    protected void driveAndFaceBoxes(int turnVal2, int drive1, int drive2, int drive3, int approachVal){

        Direction vuMark = CENTER;
        try {

            switch (vuMark) {
                case LEFT: { //largest amount
                    drive(ENCODER, drive1, 0.5);
                    break;
                }
                case RIGHT: { //smallest amount
                    drive(ENCODER, drive3, 0.5);
                    break;
                }
                case CENTER: { //middle amount
                    drive(ENCODER, drive2, 0.5);
                    break;
                }
//            case UNKNOWN:{ //middle amount
//                drive(ENCODER, drive2, 0.5);
//                break;
//            }
            }
        }
        catch (NullPointerException e)
        {
            telemetry.addData("NullPointer: switch vumark, drive w encoder","");
        }
//        Turn 90 towards cryptoboxes
        try {
            turn(CENTER, turnVal2, .4);
        }
        catch (NullPointerException e)
        {
            telemetry.addData("NullPointer: turn","");
        }
        try{
            driveStop(ENCODER, approachVal, 0.5);
        }
        catch (NullPointerException e)
        {
            telemetry.addData("NullPointer: driveStop","");
        }
    }

    protected void ejectGlyph(){
        setLift(0, 0.1);
//        Turn compression wheels to push glyph into correct column
        glyphGrabber.setPower(0.8);
        sleep(ejectTime);
        glyphGrabber.setPower(0);
    }

    protected void drivePark(int parkVal){
        driveStop(ENCODER, parkVal, 0.5);
    }
}
