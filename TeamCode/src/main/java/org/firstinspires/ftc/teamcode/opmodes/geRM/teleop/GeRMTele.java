package org.firstinspires.ftc.teamcode.opmodes.geRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.TeleSuper;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;

/**
 * Created by poofs on 11/28/2017.
 */

@TeleOp(name = "geRM - TELEOP", group = "geRM")
public class GeRMTele extends TeleSuper {

    @Override
    public void loop() {

        // DRIVE
        double max = 1.0;
        double slow = .2;
        double wheelsL = gamepad1.left_stick_y * -1;
        double wheelsR = gamepad1.right_stick_y * -1;

        if (gamepad1.dpad_up) {
            setDrive(slow);
        } else if (gamepad1.dpad_down) {
            setDrive(-slow);
        } else if (gamepad1.dpad_left) {
            setDrive(-slow, slow, -slow, slow);
        } else if (gamepad1.dpad_right) {
            setDrive(slow, -slow, slow, -slow);
        } else {
            setDrive(wheelsL * max, wheelsR * max, wheelsL * max, wheelsR * max);
        }

//      -------------------------------------------

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

//      -------------------------------------------

        // LIFT SIMPLE W/O ENCODERS
        double liftV2 = gamepad2.left_stick_y * -1;
        double scaleNum = 0.5;

        if (liftV2 > 0) {
            setLiftPower(liftV2 * scaleNum);
        } else if (liftV2 < 0) {
            setLiftPower(liftV2 * scaleNum);
        } else {
            setLiftPower(0);
        }

        // LIFT STAGES
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

//      -------------------------------------------

        // JEWEL ARM
        boolean jewelButton = gamepad1.b;
        if (jewelButton) {
            if (jewelArm.getPosition() <= .4) {
                jewelArm.setPosition(.55);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                jewelArm.setPosition(.21);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

//      -------------------------------------------
        // RELIC GRABBER

        // RELIC GRABBER: SPIN

        if(gamepad2.x) {
            clawSpinner.setPower(.7);
        }
        else if(gamepad2.y) {
            clawSpinner.setPower(-.7);
        } else {
            clawSpinner.setPower(0);
        }

        // RELIC GRABBER: EXTEND

        boolean extend = gamepad2.right_bumper;
        boolean retract = gamepad2.left_bumper;
        if (extend && retract) {
            relicArm.setPower(0);
        } else if (extend) {
            relicArm.setPower(1.0);
        } else if (retract) {
            relicArm.setPower(-1.0);
        } else {
            relicArm.setPower(0);
        }

        // RELIC GRABBER: CLAMP

        boolean clamp = gamepad2.a;
        boolean unclamp = gamepad2.b;
        if (clamp && unclamp) {
            claw.setPosition(claw.getPosition());
        } else if (clamp) {
            claw.setPosition(.1);
        } else if (unclamp) {
            claw.setPosition(.6);
        }


//      -------------------------------------------

        addTelemetry();
    }

    protected void setLiftPower(double power) {
        liftL.setPower(power);
        liftR.setPower(power);
    }
}