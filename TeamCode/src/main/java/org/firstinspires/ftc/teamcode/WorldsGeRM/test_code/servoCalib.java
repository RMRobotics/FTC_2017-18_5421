<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/WorldsGeRM/servoCalib.java
package org.firstinspires.ftc.teamcode.WorldsGeRM;
=======
package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;
>>>>>>> 94d50408e0e9249450c460feecbbdc7f57abeded:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/WorldsGeRM/test_code/servoCalib.java

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.WorldsGeRM.teleop.dumpBotTele;
import org.firstinspires.ftc.teamcode.core.TeleSuper;

/**
 * Created by Angela on 3/13/2018.
 */

public class servoCalib extends dumpBotTele {

    /**
     * Created by Angela on 2/15/2018.
     */

    @TeleOp(name = "servo calib test")
    public class testServo extends dumpBotTeleSuper {

        @Override
        public void loop() {
            if(gamepad2.a) {
                relicClaw.setPosition(relicClaw.getPosition() + .01);
            }
            else if(gamepad2.b) {
                relicClaw.setPosition(relicClaw.getPosition()-.01);
            }

            if(gamepad2.dpad_up) {
                flipForw.setPosition(flipForw.getPosition() + .01);
            }
            else if(gamepad2.dpad_down) {
                flipForw.setPosition(flipForw.getPosition() - .01);
            }

            if(gamepad2.dpad_left) {
                flipBack.setPosition(flipBack.getPosition() + .01);
            }
            else if(gamepad2.dpad_right) {
                flipBack.setPosition(flipBack.getPosition() - .01);
            }

            if(gamepad2.left_bumper) {
                gemBarWrist.setPosition(gemBarWrist.getPosition() + .01);
            }
            else if(gamepad2.right_bumper) {
                gemBarWrist.setPosition(gemBarWrist.getPosition() - .01);
            }

            if(gamepad2.left_trigger > 0) {
                gemBarShoulder.setPosition(gemBarShoulder.getPosition() + .01);
            }
            else if(gamepad2.right_trigger > 0) {
                gemBarShoulder.setPosition(gemBarShoulder.getPosition() - .01);
            }
        }
    }
}
