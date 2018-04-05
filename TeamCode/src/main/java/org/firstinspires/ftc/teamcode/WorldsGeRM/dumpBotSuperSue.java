package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ur mum gey xd on 3/22/2018.
 */

public abstract class dumpBotSuperSue extends LinearOpMode {

    //Four mecanum wheels
    protected DcMotor wheelFL, wheelFR, wheelBL, wheelBR;

    //two servos for the flipper - going in opposite directions
    protected Servo flipForw, flipBack;

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

    //timer
    protected ElapsedTime timer = new ElapsedTime();

    //color sensors on the jewel arm and bottom of the collection mechanism
    protected ColorSensor colorSensorJewel;
    protected ColorSensor colorSensorCollect;

    protected VuforiaLocalizer vuforia;
    protected VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
    protected int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    protected VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    protected VuforiaTrackable relicTemplate = relicTrackables.get(0);

    //wheelDiameterInches = 4;
    //ticksPerRotation = 1120;
    //gear ratio 1.5 to 1
    static double CPI = (1120.0 * 0.66666) / (4.0 * Math.PI); //CALCULATIONS FOR THE COUNTS PER INCH

    public void initialize(Boolean initVuforia, Boolean encoders) {
        wheelFL = hardwareMap.dcMotor.get("wheelFL");
        wheelFR = hardwareMap.dcMotor.get("wheelFR");
        wheelBL = hardwareMap.dcMotor.get("wheelBL");
        wheelBR = hardwareMap.dcMotor.get("wheelBR");
        wheelFL.setDirection(DcMotorSimple.Direction.REVERSE);
        wheelBL.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight.setDirection(DcMotorSimple.Direction.REVERSE); //opposite direction

        lift = hardwareMap.dcMotor.get("lift");

        flipForw = hardwareMap.servo.get("flipForw");
        flipBack = hardwareMap.servo.get("flipBack");
        flipBack.setDirection(Servo.Direction.REVERSE); //flip back goes in the opposite direction
        flipForw.setPosition(0);
        flipBack.setPosition(0);

        gemBarShoulder = hardwareMap.servo.get("gemBarShoulder");
        gemBarWrist = hardwareMap.servo.get("gemBarWrist");
        gemBarShoulder.setPosition(0);
        gemBarWrist.setPosition(0);

        relicExtend = hardwareMap.dcMotor.get("relicExtend");
        relicArm = hardwareMap.servo.get("relicArm");
        relicClaw = hardwareMap.servo.get("relicClaw");
        relicArm.setPosition(0);
        relicClaw.setPosition(0);

        colorSensorCollect = hardwareMap.colorSensor.get("colorSensorCollect");
        colorSensorJewel = hardwareMap.colorSensor.get("colorSensorJewel");


        if (encoders) {
            wheelFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheelFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheelBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheelBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        if (initVuforia) {
            parameters.vuforiaLicenseKey = "AckoWtn/////AAAAGan7WAnq/0UVmQZG3sp7smBgRCNBnU1p+HmsTrC+W9TyxqaMlhFirDXglelvJCX4yBiO8oou6n7UWBfdRFbKHDqz0NIo5VcNHyhelmm0yK0vGKxoU0NZbQzjh5qVWnI/HRoFjM3JOq/LB/FTXgCcEaNGhXAqnz7nalixMeP8oRQlgX5nRVX4uE6w0K4yqIc5/FIDh1tn7PldiflmvNPhOW6FukPQD3d02wEnZB/JEchSSBzDbFA10XSgtYzXiweQI5tj+D5llLRrLh0mcWeouv55oSmya5RxUC26uEuO7bCAwyolWIuUr2Wh5oAG483nTD4vFhdjVMT7f0ovLO73C6xr2AXpNwen9IExRxBeosQ4";
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
            vuforia = ClassFactory.createVuforiaLocalizer(parameters);
            relicTemplate.setName("relicVuMarkTemplate");
            telemetry.update();
            waitForStart();
            relicTrackables.activate();
        }


        waitForStart();
    }


    protected void addTelemetry() {
    }

    protected void moveEncoders(double distanceInches, int rotate) {
        //if rotate is one then the left drive train's target will be set to negative
        rotate = -rotate;
        double speed = 0.5;
        int currentPos = wheelFL.getCurrentPosition();
        int distanceTics = (int) (distanceInches * CPI);
        double tickRatio;

        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wheelBL.setTargetPosition(currentPos + distanceTics);
        wheelFL.setTargetPosition(currentPos + distanceTics);
        wheelBR.setTargetPosition(currentPos + distanceTics);
        wheelFR.setTargetPosition(currentPos + distanceTics);

        wheelBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheelFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheelBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheelFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (wheelFR.isBusy() && wheelBL.isBusy() && wheelBR.isBusy() && wheelFL.isBusy()) {
            tickRatio = (wheelFL.getCurrentPosition() - currentPos) / distanceTics;
            speed = (-0.5 * (tickRatio * tickRatio) + 0.5);
            wheelFR.setPower(speed * rotate);
            wheelFL.setPower(speed);
            wheelBR.setPower(speed * rotate);
            wheelBL.setPower(speed);
        }
    }

    public void strafe(int distance) {
        // DRIVE
        double strafe = gamepad1.left_stick_x;
        float dir = Math.signum(distance);
        int encoderStrafeFactor = 10;
        int currPos = wheelFL.getCurrentPosition();
        int endPos = distance*encoderStrafeFactor;
        while (Math.abs(endPos - currPos) > 0){
            wheelFL.setPower(strafe*dir);
            wheelFR.setPower(-strafe*dir);
            wheelBL.setPower(-strafe*dir);
            wheelBR.setPower(strafe*dir);
        }
        setDrive(0);
    }

    protected void setDrive(double p) {
        wheelFL.setPower(p);
        wheelFR.setPower(p);
        wheelBL.setPower(p);
        wheelBR.setPower(p);
    }

    protected void setDrive(double p1, double p2) {
        wheelFL.setPower(p1);
        wheelFR.setPower(p2);
        wheelBL.setPower(p1);
        wheelBR.setPower(p2);
    }

    protected void setDrive(double p1, double p2, double p3, double p4) {
        wheelFL.setPower(p1);
        wheelFR.setPower(p2);
        wheelBL.setPower(p3);
        wheelBR.setPower(p4);
    }
}