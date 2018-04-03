package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by ur mum xd
 */
@TeleOp(name="dumpBotTele", group="dumpBotConfig")
public class dumpBotTele extends org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotTeleSuper {

    public void loop()
    {
        double forward, strafe, rotate;

        forward = gamepad1.right_stick_y/2;
        strafe = gamepad1.right_stick_x/2;
        rotate = gamepad1.left_stick_x/2;


        double max = 1;
        wheelFL.setPower((forward + strafe + rotate) / max);
        wheelFR.setPower((forward - strafe - rotate) / max);
        wheelBL.setPower((forward - strafe + rotate) / max);
        wheelBR.setPower((forward + strafe - rotate) / max);

        if (gamepad1.right_trigger > 0)
            intakeRight.setPower(-1);
        else if (gamepad1.right_bumper)
            intakeRight.setPower(1);
        else
            intakeRight.setPower(0);
        if (gamepad1.left_trigger > 0)
            intakeLeft.setPower(-1);
        else if (gamepad1.left_bumper)
            intakeLeft.setPower(1);
        else
            intakeLeft.setPower(0);

        //changes from flipped to not flipped and vice versa when a is pressed
        if (gamepad2.a) {
            flipBack.setPosition(1-flipBack.getPosition());
            flipForw.setPosition(1-flipForw.getPosition());
        }

        lift.setPower(gamepad2.left_stick_y);

        relicExtend.setPower(gamepad2.right_stick_y);

        if (gamepad2.right_trigger > 0)
            relicClaw.setPosition(1);

        if (gamepad2.left_trigger > 0)
            relicClaw.setPosition(0);

        if (gamepad2.right_bumper)
            relicArm.setPosition(1);

        if (gamepad2.left_bumper)
            relicArm.setPosition(0);

    }
}
