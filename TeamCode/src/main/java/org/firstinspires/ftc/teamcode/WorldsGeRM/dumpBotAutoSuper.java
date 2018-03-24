package org.firstinspires.ftc.teamcode.worldsCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by ur mum gey xd on 3/22/2018.
 */

public abstract class dumpBotAutoSuper extends LinearOpMode{

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

    protected VuforiaLocalizer vuforia;
    protected VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
    protected int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    protected VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    protected VuforiaTrackable relicTemplate = relicTrackables.get(0);



    public void initialize(Boolean initVuforia, Boolean encoders){
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

        if (encoders){
            wheelFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheelFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheelBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheelBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        if (initVuforia){
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

}
