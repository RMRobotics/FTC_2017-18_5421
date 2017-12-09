package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by poofs on 11/28/2017.
 */

public abstract class TeleSuper extends OpMode {
    protected DcMotor FL;
    protected DcMotor FR;
    protected DcMotor BL;
    protected DcMotor BR;
    protected DcMotor glyphGrabber;
//    protected DcMotor relicArm;
//    protected Servo claw;
//    protected Servo clawSpinner;

    protected DcMotor liftL;
    protected DcMotor liftR;

    @Override
    public void init() {
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        glyphGrabber = hardwareMap.dcMotor.get("glyph");
//        relicArm = hardwareMap.dcMotor.get("relicArm");
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        glyphGrabber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftL = hardwareMap.dcMotor.get("liftL");
        liftL.setDirection(DcMotorSimple.Direction.REVERSE);
        liftR = hardwareMap.dcMotor.get("liftR");
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

//        claw = hardwareMap.servo.get("claw");
//        claw.setPosition(0);
//        clawSpinner = hardwareMap.servo.get("clawSpinner");
//        clawSpinner.setPosition(0);

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        glyphGrabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        relicArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    protected void addTelemetry() {
        telemetry.addData("1 Motor Power", FL.getPower() + " " + FR.getPower() + " " + BL.getPower() + " " + BR.getPower());
        telemetry.addData("2 Encoder", FL.getCurrentPosition() + " " + FR.getCurrentPosition() + " " + BL.getCurrentPosition() + " " + BR.getCurrentPosition());
        telemetry.addData("3 Glyph Harvester", glyphGrabber.getCurrentPosition());
        telemetry.addData("4 Lift Encoder Value", liftL.getCurrentPosition());
        telemetry.addData("Gamepad1 RB and LB", gamepad1.right_bumper + " " + gamepad1.left_bumper);
        telemetry.addData("Gamepad2 LeftStickY", gamepad2.left_stick_y);
        telemetry.addData("Gamepad2 Y and A", gamepad2.y + " " + gamepad2.a);
//        telemetry.addData("5 Relic Arm", relicArm.getCurrentPosition());
//        telemetry.addData("6 Claw", claw.getPosition());
//        telemetry.addData("7 Claw Spinner", clawSpinner.getPosition());
        telemetry.update();
    }

    protected void setDrive(double p1, double p2, double p3, double p4) {
        FL.setPower(p1);
        FR.setPower(p2);
        BL.setPower(p3);
        BR.setPower(p4);
    }

    protected void setDrive(double p){
        setDrive(p,p,p,p);
    }
}
