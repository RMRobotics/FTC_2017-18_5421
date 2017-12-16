package org.firstinspires.ftc.teamcode.opmodes.geRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.TeleSuper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * Created by poofs on 11/28/2017.
 */

@TeleOp(name = "geRM - TELEOP", group = "geRM")
public class GeRMTele extends TeleSuper{

    @Override
    public void loop() {
        // DRIVE
        double max = 1.0;
        double slow = .2;
        double wheelsL = gamepad1.left_stick_y*-1;
        double wheelsR = gamepad1.right_stick_y*-1;

        if (gamepad1.dpad_up) {
            setDrive(slow);
        } else if (gamepad1.dpad_down) {
            setDrive(-slow);
        } else if (gamepad1.dpad_left) {
            setDrive(-slow, slow, -slow, slow);
        } else if (gamepad1.dpad_right) {
            setDrive(slow, -slow, slow, -slow);
        } else {
            setDrive(wheelsL*max, wheelsR*max, wheelsL*max, wheelsR*max);
        }

        // HARVESTER
        boolean harvest = gamepad1.right_bumper;
        boolean eject = gamepad1.left_bumper;
        if (harvest && eject) {
            glyphGrabber.setPower(0);
        } else if (harvest) {
            glyphGrabber.setPower(1.0);
        } else if (eject) {
            glyphGrabber.setPower(-1.0);
        } else {
            glyphGrabber.setPower(0);
        }

        // LIFT
        int level1 = 0;
        int level2 = 500;
        int level3 = 1000;
        double lift = gamepad2.left_stick_y*-1;
        double scale = .5;
        int liftVal = (int)Math.signum(lift);
        switch (liftVal) {
            case -1:
                if (Math.abs(liftL.getCurrentPosition() - level1) > 5){
                    setLiftPower(lift*scale);
                }
                break;
            case 1:
                if (Math.abs(liftL.getCurrentPosition() - level3) > 5){
                    setLiftPower(lift*scale);
                }
                break;
            default:
                setLiftPower(0);
        }

//        boolean raiseLift = gamepad2.y;
//        boolean lowerLift = gamepad2.a;
//        if (raiseLift){
//            if (liftL.getCurrentPosition() > level1 && liftL.getCurrentPosition() < level2){
//                while (Math.abs(liftL.getCurrentPosition() - level2) > 5) {
//                    telemetry.addData("4 Lift Encoder value: ", liftL.getCurrentPosition());
//                    telemetry.update();
//                    setLiftPower(.1);
//                }
//                setLiftPower(0);
//            } else if (liftL.getCurrentPosition() > level2 && liftL.getCurrentPosition() < level3) {
//                while (Math.abs(liftL.getCurrentPosition() - level3) > 5) {
//                    telemetry.addData("Lift Encoder value: ", liftL.getCurrentPosition());
//                    telemetry.update();
//                    setLiftPower(.1);
//                }
//                setLiftPower(0);
//            }
//        } else if (lowerLift){
//            if (liftL.getCurrentPosition() > level1 && liftL.getCurrentPosition() < level2){
//                while (Math.abs(liftL.getCurrentPosition() - level1) > 5) {
//                    telemetry.addData("Lift Encoder value: ", liftL.getCurrentPosition());
//                    telemetry.update();
//                    setLiftPower(-.1);
//                }
//                setLiftPower(0);
//            } else if (liftL.getCurrentPosition() > level2 && liftL.getCurrentPosition() < level3) {
//                while (Math.abs(liftL.getCurrentPosition() - level2) > 5) {
//                    telemetry.addData("Lift Encoder value: ", liftL.getCurrentPosition());
//                    telemetry.update();
//                    setLiftPower(-.1);
//                }
//                setLiftPower(0);
//            }
//        }

        // JEWEL ARM
        boolean jewelButton = gamepad1.b;
        if (jewelButton) {
            if (jewelArm.getPosition() <= .4){
                jewelArm.setPosition(.8);
            } else {
                jewelArm.setPosition(.21);
            }
        }

        // RELIC GRABBER
        double extend = gamepad2.right_stick_y*-1;
        int extendVal = (int)Math.signum(extend);
        boolean clamp = gamepad2.a;
        boolean spinClaw = gamepad2.y;
        boolean lock = gamepad2.x;

        double extended = 3000;
//        double retracted = 0;

        double spinDown = 1.0;
        double spinUp = .5;

        double clampedPos = 1;
        double openedPos = .5;

        double locked = 0;
        double unlocked = 1;

        boolean rightButton = gamepad2.right_bumper;

        if (rightButton) {
            clawSpinner.setPosition(clawSpinner.getPosition() + .05);
        }

        boolean leftButton = gamepad2.left_bumper;

        if (leftButton) {
            clawSpinner.setPosition(clawSpinner.getPosition() - .05);
        }

        float rightTrigger = gamepad2.right_trigger;

        if (rightTrigger > 0)
        {
            clawSpinner.setPosition(clawSpinner.getPosition()+rightTrigger*0.1);
        }

        float leftTrigger = gamepad2.left_trigger;

        if (leftTrigger > 0)
        {
            clawSpinner.setPosition(clawSpinner.getPosition()+leftTrigger*-0.1);
        }

        switch (extendVal) {
            case -1:
                if (Math.abs(relicArm.getCurrentPosition()) > 5){
                    relicArm.setPower(extend);
                } else {
                    relicArm.setPower(0);
                }
                break;
            case 1:
                if (Math.abs(relicArm.getCurrentPosition()) - extended > 5){
                    relicArm.setPower(extend);
                } else {
                    relicArm.setPower(0);
                }
                break;
            default:
                relicArm.setPower(0);
        }

        if (clamp) {
            if ((Math.abs(claw.getPosition() - clampedPos) < .05)){
                claw.setPosition(openedPos);
            } else if ((Math.abs(claw.getPosition() - openedPos) < .05)){
                claw.setPosition(clampedPos);
            } else {
                claw.setPosition(openedPos);

            }
        }

        if (spinClaw){
            if ((Math.abs(clawSpinner.getPosition() - spinDown) < .08)){
                clawSpinner.setPosition(spinUp);
            } else if ((Math.abs(clawSpinner.getPosition() - spinUp) < .08)){
                clawSpinner.setPosition(spinDown);
            } else {
                clawSpinner.setPosition(spinUp);
            }
        }

        if (lock) {
            if ((Math.abs(lockServo.getPosition() - locked) < .05)){
                lockServo.setPosition(unlocked);
            } else if ((Math.abs(lockServo.getPosition() - unlocked) < .05)){
                lockServo.setPosition(locked);
            } else {
                lockServo.setPosition(locked);
            }
        }

        addTelemetry();
    }

    protected void setLiftPower(double power){
        liftL.setPower(power);
        liftR.setPower(power);
    }
}
