package org.firstinspires.ftc.teamcode.opmodes.geRM.util;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Simon on 1/11/2017.
 */

@TeleOp(name = "servoCalibration", group = "Util")
@Disabled
public class servoCalibration extends OpMode {

    private Servo jewelArm;
    private Servo claw;
    private CRServo clawSpinner;

    private double jPos;
    private double cPos;

    @Override
    public void init() {
        jewelArm = hardwareMap.servo.get("jewel");
        claw = hardwareMap.servo.get("claw");
        clawSpinner = hardwareMap.crservo.get("clawSpinner");
        jPos = jewelArm.getPosition();
        cPos = claw.getPosition();
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
            clawSpinner.setPower(-1);
        } else if (gamepad1.a) {
            clawSpinner.setPower(1);
        } else{
            clawSpinner.setPower(0);
        }

        jewelArm.setPosition(jPos);
        claw.setPosition(cPos);

        telemetry.addData("jewel pos (dpad)", jewelArm.getPosition() + " " + gamepad1.dpad_up + " " + gamepad1.dpad_down);
        telemetry.addData("claw pos (bumper)", claw.getPosition() + " " + gamepad1.right_bumper + " " + gamepad1.left_bumper);
        telemetry.addData("cspinner pos (y/a)", clawSpinner.getPower() + " " + gamepad1.y + " " + gamepad1.a);
        telemetry.update();
    }
}
