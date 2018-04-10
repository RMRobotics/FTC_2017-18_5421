package org.firstinspires.ftc.teamcode.WorldsGeRM.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by urmumxd on 11/28/2017.
 */

public abstract class dumpBotTeleSuper extends OpMode {

    //Four mecanum wheels
    protected DcMotor wheelFL, wheelFR, wheelBL, wheelBR;

    //two servos for the flipper - going in opposite directions
    protected Servo flipRight, flipLeft;

    //two servos for gem bar
    protected Servo gemBarWrist, gemBarShoulder;

    //hopefully we end up using a servo for the relic arm, but for now its a cr
    protected Servo relicArm;
    protected Servo relicClaw;
    protected DcMotor relicExtend;

    protected DcMotor lift;

    //two motors for collector thingy
    protected DcMotor intakeLeft, intakeRight;

    //for toggling flipper
    protected boolean flipped;

    @Override
    public void init() {

        wheelFL = hardwareMap.dcMotor.get("wheelFL");
        wheelFR = hardwareMap.dcMotor.get("wheelFR");
        wheelBL = hardwareMap.dcMotor.get("wheelBL");
        wheelBR = hardwareMap.dcMotor.get("wheelBR");
        wheelFL.setDirection(DcMotorSimple.Direction.REVERSE);


        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");
        //intakeRight.setDirection(DcMotorSimple.Direction.REVERSE); //opposite direction

        lift = hardwareMap.dcMotor.get("lift");

        flipLeft = hardwareMap.servo.get("flipLeft");
        flipRight = hardwareMap.servo.get("flipRight");
        flipLeft.setDirection(Servo.Direction.REVERSE); //flip back goes in the opposite direction
        flipLeft.setPosition(0.3);
        flipRight.setPosition(0.32);

//        gemBarShoulder = hardwareMap.servo.get("gemBarShoulder");
//        gemBarWrist = hardwareMap.servo.get("gemBarWrist");
//        gemBarShoulder.setPosition(0);
//        gemBarWrist.setPosition(0);
//
//        relicExtend = hardwareMap.dcMotor.get("relicExtend");
//        relicArm = hardwareMap.servo.get("relicArm");
//        relicClaw = hardwareMap.servo.get("relicClaw");
//        relicArm.setPosition(0);
//        relicClaw.setPosition(0);
    }

    protected void addTelemetry() {
    }


}



