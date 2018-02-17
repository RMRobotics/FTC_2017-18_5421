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

@TeleOp(name = "simple crservo test")
//public class testCRServo extends LinearOpMode{
//    protected CRServo clawSpinner;

//    @Override
//    public void runOpMode() throws InterruptedException {
//        clawSpinner = hardwareMap.crservo.get("clawSpinner");
//        waitForStart();
//        while(opModeIsActive()) {
//            if (gamepad1.a){
//                clawSpinner.setPower(.4);
//            } else {
//                clawSpinner.setPower(0);
//            }
//        }
//    }
//}

public class testCRServo extends TeleSuper{

    @Override
    public void loop() {
        if(gamepad2.x) {
            clawSpinner.setPower(.85);
        }
        else if(gamepad2.y) {
            clawSpinner.setPower(-.85);
        } else{
            clawSpinner.setPower(0);
        }
    }
}