package org.firstinspires.ftc.teamcode.opmodes.geRM.util;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Simon on 1/11/2017.
 */

@TeleOp(name = "servoCalibration")
public class servoCalibration extends OpMode {

    private Servo jewelArm;
    private Servo claw;
    private Servo clawSpinner;

    private double jPos;
    private double cPos;
    private double cSPos;

    @Override
    public void init() {
        jewelArm = hardwareMap.servo.get("jewel");
        claw = hardwareMap.servo.get("claw");
        clawSpinner = hardwareMap.servo.get("clawSpinner");
        jPos = jewelArm.getPosition();
        cPos = claw.getPosition();
        cSPos = clawSpinner.getPosition();
    }

    @Override
    public void loop() {
        // turns jewel arm servo
        if (gamepad1.dpad_up) {
            jPos+=.01;
        } else if (gamepad1.dpad_down) {
            jPos -= .01;
        }

        // turns claw servo
        if (gamepad1.right_bumper) {
            cPos+=.01;
        } else if (gamepad1.left_bumper) {
            cPos -= .01;
        }

        //turns claw spinner servo
        if (gamepad1.y) {
            cSPos+=.01;
        } else if (gamepad1.a) {
            cSPos -= .01;
        }

        jewelArm.setPosition(jPos);
        claw.setPosition(cPos);
        clawSpinner.setPosition(cSPos);

        telemetry.addData("jewel pos (dpad)", jewelArm.getPosition());
        telemetry.addData("claw pos (bumper)", claw.getPosition());
        telemetry.addData("cspinner pos (y/a)", clawSpinner.getPosition());
        telemetry.update();
    }
}
