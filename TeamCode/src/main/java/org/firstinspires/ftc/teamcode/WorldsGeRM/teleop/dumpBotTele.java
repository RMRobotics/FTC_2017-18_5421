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
        if (gamepad2.a) { //down
            //implement flipper here
            flipLeft.setPosition(0.83);
            flipRight.setPosition(0.83);
        }

        if (gamepad2.y) {
            //implement flipper here
            flipLeft.setPosition(0.13);
            flipRight.setPosition(0.13);
        }

        if (gamepad2.x) {
            //implement flipper here
            flipLeft.setPosition(0.63);
            flipRight.setPosition(0.63);
        }

        if (gamepad1.x){
            pushBoy.setPosition(0.9);
        }
        if (gamepad1.a){ //closes
            pushBoy.setPosition(0.1);
        }
        if (gamepad2.left_trigger!=0){          //up
            relicWrist.setPosition(1);
        }
        else if (gamepad2.right_trigger!=0){    //down
            relicWrist.setPosition(0.18);
        }
        if (gamepad2.right_bumper){
            relicClaw.setPosition(1);
        }
        if (gamepad2.left_bumper){
            relicClaw.setPosition(0);
        }
//        telemetry.addData(relicWrist.getPower() + "","");
        telemetry.update();

        if (gamepad1.y)
            gemBarShoulder.setPosition(0.93);
        if (gamepad1.b)
            gemBarShoulder.setPosition(0.12);

        if (gamepad1.right_trigger!=0)
            gemBarWrist.setPosition(0.3);
        if (gamepad1.left_trigger!=0)
            gemBarWrist.setPosition(0.84);

        if (gamepad2.left_bumper){
            glyphPusher.setPosition(.8);
        }

        if(gamepad2.right_bumper) {
            glyphPusher.setPosition(.2);
        }


        lift.setPower(-gamepad2.left_stick_y);

        relicExtend.setPower(-gamepad2.right_stick_y);

    }
}
