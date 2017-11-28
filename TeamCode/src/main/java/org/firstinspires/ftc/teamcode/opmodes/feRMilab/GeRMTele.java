package org.firstinspires.ftc.teamcode.opmodes.feRMilab;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.TeleSuper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by poofs on 11/28/2017.
 */

@TeleOp(name = "feRMi - TELEOP", group = "feRMi")
public class GeRMTele extends TeleSuper{

    @Override
    public void loop() {
// DRIVE
        double max = 1.0;
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        List l = new ArrayList<>();
        l.add(Math.abs(forward + strafe + rotate));
        l.add(Math.abs(forward - strafe - rotate));
        l.add(Math.abs(forward - strafe + rotate));
        l.add(Math.abs(forward + strafe - rotate));
        if ((double) Collections.max(l) > max) {
            max = (double) Collections.max(l);
        }

        if (gamepad1.dpad_up) {
            setDrive(1, 1, 1, 1);
        } else if (gamepad1.dpad_down) {
            setDrive(-1, -1, -1, -1);
        } else if (gamepad1.dpad_left) {
            setDrive(-1, 1, 1, -1);
        } else if (gamepad1.dpad_right) {
            setDrive(1, -1, -1, 1);
        } else {
            FL.setPower((forward + strafe + rotate) / max);
            FR.setPower((forward - strafe - rotate) / max);
            BL.setPower((forward - strafe + rotate) / max);
            BR.setPower((forward + strafe - rotate) / max);
        }

        // HARVESTER AND BELT
        boolean harvest = gamepad1.right_bumper;
        boolean eject = gamepad1.left_bumper;
        if (harvest && eject) {
            belt.setPower(0);
        } else if (harvest) {
            belt.setPower(1.0);
        } else if (eject) {
            belt.setPower(-1.0);
        } else {
            belt.setPower(0);
        }

        // FLYWHEEL
        if (gamepad2.y) {
            flyL.setPower(0.93);
            flyR.setPower(0.93);
        } else if (gamepad2.a) {
            flyL.setPower(-SCALED_POWER);
            flyR.setPower(-SCALED_POWER);
        } else if (gamepad2.right_bumper) {
            flyL.setPower(1);
            flyR.setPower(1);
        } else {
            flyL.setPower(0);
            flyR.setPower(0);
        }

        // INDEXER
        if (gamepad2.left_bumper){
            index.setPosition(.5);
        } else {
            index.setPosition(.12);
        }

        // LIFT
        if (gamepad2.left_trigger > 0.3) {
            lift.setPower(-gamepad2.left_trigger);
        } else if (gamepad2.right_trigger > 0.3) {
            lift.setPower(gamepad2.right_trigger/2);
        } else {
            lift.setPower(0);
        }

        // LIFT DEPLOYMENT
        if (gamepad2.dpad_up) {
            triggered = true;
        }
        if (gamepad2.dpad_down) {
            liftHold.setPosition(0.93);
        } else if (triggered){
            liftHold.setPosition(0.35);
        } else {
            liftHold.setPosition(0.03);
        }

        addTelemetry();
    }
}
