package org.firstinspires.ftc.teamcode.core;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.util.enums.Color;
import org.firstinspires.ftc.teamcode.util.enums.Direction;
import org.firstinspires.ftc.teamcode.util.enums.Drive;

import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by poofs on 11/21/2017.
 */

public abstract class GeRMLinear extends LinearOpMode {
    protected ElapsedTime runtime = new ElapsedTime();

    protected DcMotor FL;
    protected DcMotor FR;
    protected DcMotor BL;
    protected DcMotor BR;
    protected DcMotor glyphGrabber;
    protected DcMotor liftL;
    protected DcMotor liftR;

    protected Servo jewelArm;

    BNO055IMU imu1;
    protected ColorSensor colorSensor;

    protected int scale;
    protected double initTime;

    protected Direction jewel;

    protected Servo claw;
    protected CRServo clawSpinner;

    protected VuforiaTrackable relicTemplate;

    protected double voltageSensor;

    public void initialize(Color c, DcMotor.RunMode r, Direction direction) {

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

//        voltageSensor = hardwareMap.voltageSensor.get("Motor Controller 1").getVoltage();

//        liftL = hardwareMap.dcMotor.get("liftL");
//        liftR = hardwareMap.dcMotor.get("liftR");
//        liftL.setDirection(DcMotorSimple.Direction.REVERSE);
//        liftR.setDirection(DcMotorSimple.Direction.REVERSE);
//
        glyphGrabber = hardwareMap.dcMotor.get("glyph");
        glyphGrabber.setDirection(DcMotorSimple.Direction.REVERSE);
        glyphGrabber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        // servo initialization
        jewelArm = hardwareMap.servo.get("jewel");
        jewelArm.setPosition(0.15);
//
//        claw = hardwareMap.servo.get("claw");
//        claw.setPosition(1);
//
//        clawSpinner = hardwareMap.servo.get("clawSpinner");
//        clawSpinner.setPosition(.25);

        colorSensor = hardwareMap.colorSensor.get("color");

//        relicArm = hardwareMap.crservo.get("relicArm");
//        relicArm.setPower(0);

//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu1 = hardwareMap.get(BNO055IMU.class, "imu1");
//        imu1.initialize(parameters);

        switch (direction) {
            case FORWARD:
                scale = 1;
                break;
            case BACKWARD:
                scale = -1;
                break;
            default:
                scale = -1;
                break;
        }
        telemetry.addData("", "VUFORIA INITIALIZING! DONT PRESS START!!");
        telemetry.update();

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

        waitForStart();

//         vuforia activate
        relicTrackables.activate();
    }

    protected void setLift(int val, double power) {
        val = val * scale;
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (Math.abs(liftL.getCurrentPosition() - val) > 5 && opModeIsActive()) {
            telemetry.addData("current Encoder value: ", liftL.getCurrentPosition());
            telemetry.update();
            liftL.setPower(power);
            liftR.setPower(power);
        }
        liftL.setPower(0);
        liftR.setPower(0);
    }

    protected void poseTelemetry(OpenGLMatrix pose) {
        VectorF trans = pose.getTranslation();
        Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

        // Extract the X, Y, and Z components of the offset of the target relative to the robot
        int tX = (int) trans.get(0);
        int tY = (int) trans.get(1);
        int tZ = (int) trans.get(2);
        telemetry.addData("Trans", tX + ", " + tY + ", " + tZ);
        // X is side to side
        // Y is up and down
        // Z is towards and away, normal distance to pictograph

        // Extract the rotational components of the target relative to the robot
        int rX = (int) rot.firstAngle;
        int rY = (int) rot.secondAngle;
        int rZ = (int) rot.thirdAngle;
        telemetry.addData("Rot", rX + ", " + rY + ", " + rZ);
    }

    protected void driveStop(Drive type, int val, double power) {
        drive(type, val, power);
        setDrive(0);
    }

    protected void driveAccelerate(int val, double power) {
        double mag = Math.abs(power);
        val = val * scale;
        double dir = Math.signum(val - FL.getCurrentPosition());
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                setEnc(val);
        int shift = 0;
        // TODO: check to see if acceleration code functions properly
        while (Math.abs(FR.getCurrentPosition() - val) > 5 && opModeIsActive()) {
            telemetry.addData("current Encoder value: ", FR.getCurrentPosition());
            telemetry.update();
            if (shift * 0.02 < mag) {
                setDrive(scale * dir * shift * 0.05);
                shift++;
                //increase power gradually
                sleep(200);
            } else {
                setDrive(scale * dir * mag);
            }
        }
    }

    protected void drive(Drive type, int val, double power) {
        switch (type) {
            case TIME:
                initTime = runtime.milliseconds();
                while (runtime.milliseconds() - initTime < val && opModeIsActive()) {
                    setDrive(power);
                }
                break;
            case ENCODER:
                val = FR.getCurrentPosition() + val;
                setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                while (Math.abs(FR.getCurrentPosition() - val) > 5 && opModeIsActive()) {
                    addTelemetry();
                    setDrive(power);
                }
                break;
//            case RANGE:
//                float delta = val - rangeReader.read(0x04, 2)[0];
//                float dir = Math.signum(delta);
//                if (dir > 0) {
//                    while (rangeReader.read(0x04, 2)[0] < val && opModeIsActive()) {
//                        setDrive(-scale * power);
//                    }
//                } else if (dir < 0) {
//                    while (rangeReader.read(0x04, 2)[0] > val && opModeIsActive()) {
//                        setDrive(scale * power);
//                    }
//                }
//                break;
            default:
                break;
        }
    }

    protected void turnByTime(Direction dir, double power, double time) {
        switch (dir) {
            case LEFT:
                setDrive(-power, power);
                sleep((long) time);
                break;
            case RIGHT:
                setDrive(power, -power);
                sleep((long) time);
                break;
            default:
                setDrive(0, 0);
        }
        setDrive(0, 0);
    }



    protected void turn(Direction side, int degree, double power) {
        // fi1nds the difference between the target and the starting angle
//        float delta = degree - navx.getYaw();
        float delta = degree;

        // sets the magnitude of the turn (absolute value of delta)
        float mag = Math.abs(delta);

        // whether or not you are turning left or right
        float dir = Math.signum(delta);

        // while robot is more than 2 degrees away from the target angle
        while (mag > 5 && opModeIsActive()) {
            if (mag < 10) {
                power = 0.3;
            }
            switch (side) {
                case CENTER:
                    setDrive(dir * power, -dir * power);
                    break;
                case LEFT:
                    setDrive(dir * power, 0);
                    break;
                case RIGHT:
                    setDrive(0, -dir * power);
                    break;
                default:
                    setDrive(0);
                    break;
            }
//            telemetry.addData("NavX Yaw", navx.getYaw());
            telemetry.addData("delta", delta);
            telemetry.addData("mag", mag);
            telemetry.addData("dir", dir);
            telemetry.addData("power", power);
            telemetry.addData("Motor Power", FL.getPower() + " " + FR.getPower() + " " + BL.getPower() + " " + BR.getPower());
            telemetry.update();
            // update distance from target angle
//            delta = degree - navx.getYaw();
            mag = Math.abs(delta);
            dir = Math.signum(delta);
        }
        setDrive(0);
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

    protected void setEnc(int p) {
        FL.setTargetPosition(FL.getCurrentPosition() + p);
        FR.setTargetPosition(FR.getCurrentPosition() + p);
        BL.setTargetPosition(BL.getCurrentPosition() + p);
        BR.setTargetPosition(BR.getCurrentPosition() + p);
    }

    protected void setEnc(int p1, int p2, int p3, int p4) {
        FL.setTargetPosition(FL.getCurrentPosition() + p1);
        FR.setTargetPosition(FR.getCurrentPosition() + p2);
        BL.setTargetPosition(BL.getCurrentPosition() + p3);
        BR.setTargetPosition(BR.getCurrentPosition() + p4);
    }
}