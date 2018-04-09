package org.firstinspires.ftc.teamcode.WorldsGeRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by ur mum xd
 */
@TeleOp(name="dumpBotTele", group="dumpBotConfig")
public class dumpBotTele extends dumpBotTeleSuper {

    public void loop()
    {

        double forward, strafe, rotate;
        forward = -gamepad1.right_stick_y;
        strafe = gamepad1.right_stick_x;
        rotate = gamepad1.left_stick_x;


        double max = 1;
        wheelFL.setPower((forward + strafe + rotate) / max);
        wheelFR.setPower((forward - strafe - rotate) / max);
        wheelBL.setPower((forward - strafe + rotate) / max);
        wheelBR.setPower((forward + strafe - rotate) / max);


        if (gamepad1.left_bumper) {
            intakeRight.setPower(1);
            intakeLeft.setPower(-1);
        }
        else if (gamepad1.right_bumper){
            intakeRight.setPower(-1);
            intakeLeft.setPower(1);
        }
        else {
            intakeLeft.setPower(0);
            intakeRight.setPower(0);
        }

        //changes from flipped to not flipped and vice versa when a is pressed
        if (gamepad2.y) {
            //implement flipper here
            flipLeft.setPosition(0.9);
            flipRight.setPosition(0.92);
        }

        if (gamepad2.a) {
            //implement flipper here
            flipLeft.setPosition(0.1);
            flipRight.setPosition(0.12);
        }

        if (gamepad2.x) {
            //implement flipper here
            flipLeft.setPosition(0.49);
            flipRight.setPosition(0.51);
        }


        lift.setPower(-gamepad2.left_stick_y);

        //relicExtend.setPower(gamepad2.right_stick_y);

//        if (gamepad2.right_trigger > 0)
//            relicClaw.setPosition(1);
//
//        if (gamepad2.left_trigger > 0)
//            relicClaw.setPosition(0);
//
//        if (gamepad2.right_bumper)
//            relicArm.setPosition(1);
//
//        if (gamepad2.left_bumper)
//            relicArm.setPosition(0);

    }
}