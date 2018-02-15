package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by poofs on 11/28/2017.
 */

public abstract class TeleSuper extends OpMode {
    protected DcMotor FL;
    protected DcMotor FR;
    protected DcMotor BL;
    protected DcMotor BR;

    protected DcMotor glyphGrabber;
    protected Servo jewelArm;

    protected DcMotor relicArm;
    protected Servo claw;
    protected CRServo clawSpinner;

    protected DcMotor liftL;
    protected DcMotor liftR;
    protected ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        glyphGrabber = hardwareMap.dcMotor.get("glyph");
        glyphGrabber.setDirection(DcMotorSimple.Direction.REVERSE);
        glyphGrabber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        glyphGrabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        jewelArm = hardwareMap.servo.get("jewel");

        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");
        liftL.setDirection(DcMotorSimple.Direction.REVERSE);
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        claw = hardwareMap.servo.get("claw");

        clawSpinner = hardwareMap.crservo.get("clawSpinner");
//
        relicArm = hardwareMap.dcMotor.get("relicArm");
        relicArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        relicArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        jewelArm.setPosition(0.21);
        claw.setPosition(.8);
    }

    protected void addTelemetry() {
        telemetry.addData("1 Motor Power", FL.getPower() + " " + FR.getPower() + " " + BL.getPower() + " " + BR.getPower());
        telemetry.addData("2 Encoder", FL.getCurrentPosition() + " " + FR.getCurrentPosition() + " " + BL.getCurrentPosition() + " " + BR.getCurrentPosition());
        telemetry.addData("3 Glyph Harvester", glyphGrabber.getCurrentPosition());
        telemetry.addData("4 Lift Encoder Value", liftL.getCurrentPosition());
        telemetry.addData("Gamepad2 Right Joystick", -1*gamepad2.right_stick_y);
        telemetry.addData("dpad values", gamepad2.dpad_down + " " + gamepad2.dpad_up);
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
