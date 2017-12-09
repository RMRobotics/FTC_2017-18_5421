package org.firstinspires.ftc.teamcode.opmodes.geRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.TeleSuper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by poofs on 11/28/2017.
 */

@TeleOp(name = "geRM - TELEOP", group = "feRMi")
public class GeRMTele extends TeleSuper{

    @Override
    public void loop() {
        // DRIVE
        double max = 1.0;
        double slow = .3;
        double forward = gamepad1.left_stick_y;
        double rotate = gamepad1.right_stick_x;

        List l = new ArrayList<>();
        l.add(Math.abs(forward + rotate));
        l.add(Math.abs(forward - rotate));
        l.add(Math.abs(forward + rotate));
        l.add(Math.abs(forward - rotate));
        if ((double) Collections.max(l) > max) {
            max = (double) Collections.max(l);
        }

        if (gamepad1.dpad_up) {
            setDrive(slow);
        } else if (gamepad1.dpad_down) {
            setDrive(-slow);
        } else if (gamepad1.dpad_left) {
            setDrive(-slow, slow, slow, -slow);
        } else if (gamepad1.dpad_right) {
            setDrive(slow, -slow, -slow, slow);
        } else {
            FL.setPower((forward + rotate) / max);
            FR.setPower((forward - rotate) / max);
            BL.setPower((forward + rotate) / max);
            BR.setPower((forward - rotate) / max);
        }

        // HARVESTER
        boolean harvest = gamepad2.a;
        boolean eject = gamepad2.y;
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
        double lift = gamepad2.left_stick_y;
        liftL.setPower(lift);
        liftR.setPower(lift);

        boolean raiseLift = gamepad2.right_bumper;
        boolean lowerLift = gamepad2.left_bumper;
        int level1 = 0;
        int level2 = 500;
        int level3 = 1000;
        if (raiseLift){
            if (liftL.getCurrentPosition() > level1 && liftL.getCurrentPosition() < level2){
                while (Math.abs(liftL.getCurrentPosition() - level2) > 5) {
                    telemetry.addData("current Lift Encoder value: ", liftL.getCurrentPosition());
                    telemetry.update();
                    setLiftPower(.1);
                }
                setLiftPower(0);
            } else if (liftL.getCurrentPosition() > level2 && liftL.getCurrentPosition() < level3) {
                while (Math.abs(liftL.getCurrentPosition() - level3) > 5) {
                    telemetry.addData("current Lift Encoder value: ", liftL.getCurrentPosition());
                    telemetry.update();
                    setLiftPower(.1);
                }
                setLiftPower(0);
            }
        } else if (lowerLift){
            if (liftL.getCurrentPosition() > level1 && liftL.getCurrentPosition() < level2){
                while (Math.abs(liftL.getCurrentPosition() - level1) > 5) {
                    telemetry.addData("current Lift Encoder value: ", liftL.getCurrentPosition());
                    telemetry.update();
                    setLiftPower(-.1);
                }
                setLiftPower(0);
            } else if (liftL.getCurrentPosition() > level2 && liftL.getCurrentPosition() < level3) {
                while (Math.abs(liftL.getCurrentPosition() - level2) > 5) {
                    telemetry.addData("current Lift Encoder value: ", liftL.getCurrentPosition());
                    telemetry.update();
                    setLiftPower(-.1);
                }
                setLiftPower(0);
            }
        }

        // ClAMP RELIC GRABBER
        float clamp = gamepad2.left_trigger;
        float unclamp = gamepad2.right_trigger;
        double maxClamp = .2;
        double clampedPos = 1;
        double openedPos = 0;

        if (clamp > 0 && clampedPos - relicGrabber.getPosition() <= .2){
            relicGrabber.setPosition(relicGrabber.getPosition() + maxClamp*clamp);
        } else if (unclamp > 0 && openedPos + relicGrabber.getPosition() <= .2){
            relicGrabber.setPosition(relicGrabber.getPosition() - maxClamp*unclamp);
        }

        addTelemetry();
    }

    protected void setLiftPower(double power){
        liftL.setPower(power);
        liftR.setPower(power);
    }
}
