package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.util.enums.Direction;

import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by michaelblob on 1/16/18.
 */

@Autonomous(name = "RED2: Auto")
public class BareTest extends LinearOpMode {

    protected ElapsedTime runtime = new ElapsedTime();

    protected DcMotor FL;
    protected DcMotor FR;
    protected DcMotor BL;
    protected DcMotor BR;
    protected DcMotor glyphGrabber;
    protected DcMotor liftL;
    protected DcMotor liftR;

    protected Servo jewelArm;

    protected AHRS navx;

    protected ColorSensor colorSensor;

    protected DeviceInterfaceModule dim;

    protected int scale;
    protected double initTime;

    protected Direction jewel;
    protected Servo claw;
    protected Servo clawSpinner;
    protected Servo lockServo;

    protected VuforiaTrackable relicTemplate;

    @Override
    public void runOpMode() throws InterruptedException {
        // motor initialization
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        setZeroMode(DcMotor.ZeroPowerBehavior.BRAKE);
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(r);

        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");
        liftL.setDirection(DcMotorSimple.Direction.REVERSE);
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        glyphGrabber = hardwareMap.dcMotor.get("glyph");
        glyphGrabber.setDirection(DcMotorSimple.Direction.REVERSE);
        glyphGrabber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // servo initialization
        jewelArm = hardwareMap.servo.get("jewel");
        jewelArm.setPosition(0.21);

        claw = hardwareMap.servo.get("claw");
        claw.setPosition(1);

        clawSpinner = hardwareMap.servo.get("clawSpinner");
        clawSpinner.setPosition(.25);

        lockServo = hardwareMap.servo.get("lock");
        lockServo.setPosition(1);

        colorSensor = hardwareMap.colorSensor.get("color");

//        switch (direction) {
//            case FORWARD:
//                scale = 1;
//                break;
//            case BACKWARD:
//                scale = -1;
//                break;
//            default:
//                scale = -1;
//                break;
//        }
        scale = 1;

//         navx initialization and calibration
        dim = hardwareMap.deviceInterfaceModule.get("dim");
        navx = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"), 0, AHRS.DeviceDataType.kProcessedData, (byte) 50);
        while (!navx.isConnected()) {
            telemetry.addData("NavX Connection", "NOT CONNECTED");
            telemetry.update();
        }
        while (navx.isCalibrating()) {
            telemetry.addData("NavX Status", "CALIBRATION IN PROCESS");
            telemetry.update();
        }
        telemetry.addData("VUFORIA Status", "VUFORIA STARTING UP! DONT START");
        telemetry.update();


//        // range finder
//        range = hardwareMap.i2cDevice.get("range");
//        rangeReader = new I2cDeviceSynchImpl(range, I2cAddr.create8bit(0x60), false);
//        rangeReader.engage();

//        Vuforia Initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AY77tqP/////AAAAGfLr0EwiUEvBgqYkqzIkmW1s7GIs/g3aXlDXMXvvOAN8V1hF4ZLx8qOibfX//3q6tSGlobO4cnOU27ue2pwMeg5Z10jgtWm2S01GM1FcFYr1LFSl/MGT/2KJ+zTv0051h3MvcY8/o9pKTGsTuBA9gJ1Cfm48BLNp8kbftffjMPpuCQZapAstwIF5KsZZ2WY6JDdUNiJfU6YcML5Q+DSRM+wF8zf5iiKavSG2WW6jP1f8RukTPjFGdRJsoz05ktSJ/xi6sKh+vTlLU92K7yO38pwJ3nfPOQJrtoE8OBgzRLMvWz9UwaswWps0NJPyr8iOTGsixtWO35lZjUzP5hDkNLhzl1DFRLJUQPnltmhBif5c";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        sleep(1500);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        runtime.reset(); // reset runtime counter
        navx.zeroYaw(); // reset navx yaw value

        waitForStart();

        // vuforia activate
        relicTrackables.activate();

        // DRIVE FORWARD A LITTLE
        setDrive(1.0);
        sleep(2000);
        setDrive(0.0);
        sleep(2000);
        setDrive(-1.0);
        sleep(2000);
        stop();
    }

    protected void addTelemetry() {
//        telemetry.addData("1 Time", runtime.seconds());
//        telemetry.addData("2 Yaw", navx.getYaw());
//        telemetry.addData("6 Color Red and Blue", colorSensor.red() + " and " + colorSensor.blue());
//        telemetry.addData("7 Range", rangeReader.read(0x04, 2)[0] + " " + rangeReader.read(0x04, 2)[1]);
        telemetry.addData("8 Motor Power", FL.getPower() + " " + FR.getPower() + " " + BL.getPower() + " " + BR.getPower());
        telemetry.addData("9 Encoder Vals", FL.getCurrentPosition() + " " + FR.getCurrentPosition() + " " + BL.getCurrentPosition() + " " + BR.getCurrentPosition());
        telemetry.update();
    }

    protected void setMode(DcMotor.RunMode r) {
        FL.setMode(r);
        FR.setMode(r);
        BL.setMode(r);
        BR.setMode(r);
    }

    protected void setZeroMode(DcMotor.ZeroPowerBehavior z) {
        FL.setZeroPowerBehavior(z);
        FR.setZeroPowerBehavior(z);
        BL.setZeroPowerBehavior(z);
        BR.setZeroPowerBehavior(z);
    }

    protected void setDrive(double p) {
        FL.setPower(p);
        FR.setPower(p);
        BL.setPower(p);
        BR.setPower(p);
    }

    protected void setDrive(double p1, double p2) {
        FL.setPower(p1);
        FR.setPower(p2);
        BL.setPower(p1);
        BR.setPower(p2);
    }

    protected void setDrive(double p1, double p2, double p3, double p4) {
        FL.setPower(p1);
        FR.setPower(p2);
        BL.setPower(p3);
        BR.setPower(p4);
    }

}
