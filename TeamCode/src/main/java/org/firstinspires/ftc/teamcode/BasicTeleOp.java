package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by tina on 10/24/17.
 */

public class BasicTeleOp extends OpMode {

    private ElapsedTime timer;
    private DcMotor BR;
    private DcMotor BL;
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor glyphGrabber;
    private DcMotor liftR;
    private DcMotor liftL;
    private DcMotor relicGrabber;
    private Servo relicHand;
    private Servo relicArm;
    //private Servo;

    public void init(){
        BR = hardwareMap.dcMotor.get("wheelBR");
        BL = hardwareMap.dcMotor.get("wheelBL");
        FL = hardwareMap.dcMotor.get("wheelFL");
        FR = hardwareMap.dcMotor.get("wheelFR");
        relicHand = hardwareMap.servo.get("relicHand");
        relicArm = hardwareMap.servo.get("relicArm");
        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");
        glyphGrabber = hardwareMap.dcMotor.get("glyphGrabber");
        relicGrabber = hardwareMap.dcMotor.get("relicGrabber");
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.FORWARD);
        BR.setPower(0);
        BL.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    public void loop(){

        // DRIVE
        double max = 1.0;
        double forward = -gamepad1.left_stick_y;
        double rotate = gamepad1.right_stick_x;

        List l = new ArrayList<>();
        l.add(Math.abs(forward + rotate));
        l.add(Math.abs(forward - rotate));
        if ((double) Collections.max(l) > max) {
            max = (double) Collections.max(l);
        }

        if (gamepad1.dpad_up) {
            setDrive(1, 1, 1, 1);
        } else if (gamepad1.dpad_down) {
            setDrive(-1, -1, -1, -1);
        } else if (gamepad1.dpad_left) {
            setDrive(-1, 1, 1, -1);
        } else if (gamepad1.dpad_right) {
            setDrive(1, -1, -1, 1);
        } else {
            FL.setPower((forward + rotate) / max);
            BL.setPower((forward + rotate) / max);
            FR.setPower((forward - rotate) / max);
            BR.setPower((forward - rotate) / max);

        }



        // INDEXER
        if (gamepad2.left_bumper){
            relicHand.setPosition(.5);
        } else {
            relicHand.setPosition(.12);
        }


    }

    protected void setDrive(double p1, double p2, double p3, double p4) {
        FL.setPower(p1);
        FR.setPower(p2);
        BL.setPower(p3);
        BR.setPower(p4);
    }

}
