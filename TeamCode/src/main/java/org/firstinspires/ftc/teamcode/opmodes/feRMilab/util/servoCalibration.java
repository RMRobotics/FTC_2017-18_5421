package org.firstinspires.ftc.teamcode.opmodes.feRMilab.util;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Simon on 1/11/2017.
 */

@TeleOp(name = "servoCalibration")
@Disabled
public class servoCalibration extends OpMode {

    private Servo servo;
    private double servoValue = 0.5;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("liftHold");
    }

    @Override
    public void loop() {
        //turns beacon pusher servo;+
        if (gamepad1.x) {
            servoValue+=.01;
        } else if (gamepad1.b) {
            servoValue -= .01;
        }
        servo.setPosition(servoValue);
        telemetry.addData("pos", servo.getPosition());
    }
}
