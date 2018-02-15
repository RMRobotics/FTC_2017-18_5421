package org.firstinspires.ftc.teamcode.opmodes.geRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.core.TeleSuper;

/**
 * Created by Angela on 2/15/2018.
 */

@TeleOp(name = "clamp test")

public class testCRServo extends TeleSuper{

    @Override
    public void loop() {
        boolean clamp = gamepad2.a;
        boolean unclamp = gamepad2.b;
        if (gamepad1.a) {
            claw.setPosition(.1);
        } else if (gamepad1.b) {
            claw.setPosition(.4);
        }
    }
}