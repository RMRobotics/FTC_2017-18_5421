package org.firstinspires.ftc.teamcode.opmodes.geRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.TeleSuper;

/**
 * Created by Angela on 3/13/2018.
 */

public class servoCalib {

    /**
     * Created by Angela on 2/15/2018.
     */

    @TeleOp(name = "servo calib test")
    public class testServo extends TeleSuper {

        @Override
        public void loop() {
            if(gamepad2.x) {
                relicArm.setPower(.85);
            }
            else if(gamepad2.y) {
                relicArm.setPower(-.85);
            } else{
                relicArm.setPower(0);
            }

            if(gamepad2.a) {
                relicClaw.setPower(.85);
            }
            else if(gamepad2.b) {
                relicClaw.setPower(-.85);
            } else{
                relicClaw.setPower(0);
            }

            if(gamepad2.dpad_up) {
                flipForw.setPower(.85);
            }
            else if(gamepad2.dpad_down) {
                flipForw.setPower(-.85);
            } else{
                flipForw.setPower(0);
            }

            if(gamepad2.dpad_left) {
                flipBack.setPower(.85);
            }
            else if(gamepad2.dpad_right) {
                flipBack.setPower(-.85);
            } else{
                flipBack.setPower(0);
            }

            if(gamepad2.left_bumper) {
                gemBarWrist.setPower(.85);
            }
            else if(gamepad2.right_bumper) {
                gemBarWrist.setPower(-.85);
            } else{
                gemBarWrist.setPower(0);
            }

            if(gamepad2.left_trigger > 0) {
                gemBarShoulder.setPower(.85);
            }
            else if(gamepad2.right_trigger > 0) {
                gemBarShoulder.setPower(-.85);
            } else{
                gemBarShoulder.setPower(0);
            }
        }
    }
}
