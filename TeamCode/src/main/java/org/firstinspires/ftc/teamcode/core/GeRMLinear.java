package org.firstinspires.ftc.teamcode.core;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
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
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.util.enums.Color;
import org.firstinspires.ftc.teamcode.util.enums.Direction;
import org.firstinspires.ftc.teamcode.util.enums.Drive;

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

    protected AHRS navx;

    protected I2cDevice colorSensor;
    protected I2cDeviceSynch colorSensorReader;

    protected DeviceInterfaceModule dim;

    protected I2cDevice range;
    protected I2cDeviceSynch rangeReader;

    protected int scale;
    protected double initTime;

    protected Direction jewel;
//
    protected VuforiaLocalizer vuforia;
    protected VuforiaTrackable relicTemplate;
    protected VuforiaTrackables relicTrackables;

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

        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");
        liftL.setDirection(DcMotorSimple.Direction.REVERSE);
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        glyphGrabber = hardwareMap.dcMotor.get("glyph");
        glyphGrabber.setDirection(DcMotorSimple.Direction.REVERSE);
        glyphGrabber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // servo initialization
        jewelArm = hardwareMap.servo.get("jewel");

        // navx initialization and calibration
        dim = hardwareMap.deviceInterfaceModule.get("dim");
        navx = AHRS.getInstance(dim, 0, AHRS.DeviceDataType.kProcessedData, (byte) 50);
        telemetry.addData("Status", "found navx");
        while (navx.isCalibrating()) {
            telemetry.addData("Status", !navx.isCalibrating());
            telemetry.update();
        }

        // center color sensor
        colorSensor = hardwareMap.i2cDevice.get("color");
        colorSensorReader = new I2cDeviceSynchImpl(colorSensor, I2cAddr.create8bit(0x3c), false);
        colorSensorReader.engage();
        colorSensorReader.write8(3,0); //edit values

//        // range finder
//        range = hardwareMap.i2cDevice.get("range");
//        rangeReader = new I2cDeviceSynchImpl(range, I2cAddr.create8bit(0x60), false);
//        rangeReader.engage();

        //Vuforia Initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        params.vuforiaLicenseKey = "AY77tqP/////AAAAGfLr0EwiUEvBgqYkqzIkmW1s7GIs/g3aXlDXMXvvOAN8V1hF4ZLx8qOibfX//3q6tSGlobO4cnOU27ue2pwMeg5Z10jgtWm2S01GM1FcFYr1LFSl/MGT/2KJ+zTv0051h3MvcY8/o9pKTGsTuBA9gJ1Cfm48BLNp8kbftffjMPpuCQZapAstwIF5KsZZ2WY6JDdUNiJfU6YcML5Q+DSRM+wF8zf5iiKavSG2WW6jP1f8RukTPjFGdRJsoz05ktSJ/xi6sKh+vTlLU92K7yO38pwJ3nfPOQJrtoE8OBgzRLMvWz9UwaswWps0NJPyr8iOTGsixtWO35lZjUzP5hDkNLhzl1DFRLJUQPnltmhBif5c";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        this.vuforia = ClassFactory.createVuforiaLocalizer(params);
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);

        // set LED to alliance color
        switch (c) {
            case RED:
                dim.setLED(0, false); // blue
                dim.setLED(1, true); // red
                break;
            case BLUE:
                dim.setLED(1, false);
                dim.setLED(0, true);
                break;
            case NEITHER:
                dim.setLED(1, false);
                dim.setLED(1, true);
                break;
            default:
                dim.setLED(0, false);
                dim.setLED(0, true);
                break;
        }

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

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset(); // reset runtime counter
        navx.zeroYaw(); // reset navx yaw value

        // initialize servo positions
        jewelArm.setPosition(0.21);
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
            case RANGE:
                float delta = val - rangeReader.read(0x04, 2)[0];
                float dir = Math.signum(delta);
                if (dir > 0) {
                    while (rangeReader.read(0x04, 2)[0] < val && opModeIsActive()) {
                        setDrive(-scale * power);
                    }
                } else if (dir < 0) {
                    while (rangeReader.read(0x04, 2)[0] > val && opModeIsActive()) {
                        setDrive(scale * power);
                    }
                }
                break;
            default:
                break;
        }
    }

    protected void turn(Direction side, int degree, double power) {
        // finds the difference between the target and the starting angle
        float delta = degree - navx.getYaw();

        // sets the magnitude of the turn (absolute value of delta)
        float mag = Math.abs(delta);

        // whether or not you are turning left or right
        float dir = Math.signum(delta);

        // while robot is more than 2 degrees away from the target angle
        while (mag > 2 && opModeIsActive()) {
            if (mag < 12) {
                power = 0.07;
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

            // update distance from target angle
            delta = degree - navx.getYaw();
            mag = Math.abs(delta);
            dir = Math.signum(delta);
        }
        setDrive(0);
    }

    protected void addTelemetry() {
        telemetry.addData("1 Time", runtime.seconds());
        telemetry.addData("2 Yaw", navx.getYaw());
        telemetry.addData("6 Color", colorSensorReader.read(0x04, 1)[0] & 0xFF);
//        telemetry.addData("7 Range", rangeReader.read(0x04, 2)[0] + " " + rangeReader.read(0x04, 2)[1]);
        telemetry.addData("8 Motor", FL.getPower() + " " + FR.getPower() + " " + BL.getPower() + " " + BR.getPower());
        telemetry.addData("9 Encoder", FL.getCurrentPosition() + " " + FR.getCurrentPosition() + " " + BL.getCurrentPosition() + " " + BR.getCurrentPosition());
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