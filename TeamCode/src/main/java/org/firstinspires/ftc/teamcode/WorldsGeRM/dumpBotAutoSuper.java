package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.WorldsGeRM.IIMU;
import org.firstinspires.ftc.teamcode.WorldsGeRM.RevIMU;

/**
 * Created by ur mum gey xd on 3/22/2018.
 */

public abstract class dumpBotAutoSuper extends LinearOpMode{

    //Four mecanum wheels
    protected DcMotor wheelFL, wheelFR, wheelBL, wheelBR;

    //two servos for the flipper - going in opposite directions
    protected Servo flipLeft, flipRight;

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
    protected VuforiaLocalizer.Parameters parameter = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    protected VuforiaTrackable relicTemplate = relicTrackables.get(0);

    //wheelDiameterInches = 4;
    //ticksPerRotation = 1120;
    //gear ratio 1.5 to 1
    static double CPI = (1120.0 * 0.66666)/(4.0 * Math.PI); //CALCULATIONS FOR THE COUNTS PER INCH

    protected BNO055IMU rev;
    protected IIMU imu;

    // State used for updating telemetry
    protected Orientation angles;
    protected Acceleration gravity;

    public void initialize(Boolean initVuforia, Boolean encoders){
        wheelFL = hardwareMap.dcMotor.get("wheelFL");
        wheelFR = hardwareMap.dcMotor.get("wheelFR");
        wheelBL = hardwareMap.dcMotor.get("wheelBL");
        wheelBR = hardwareMap.dcMotor.get("wheelBR");
        wheelFL.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");

        lift = hardwareMap.dcMotor.get("lift");

        flipLeft = hardwareMap.servo.get("flipLeft");
        flipRight = hardwareMap.servo.get("flipRight");
        flipLeft.setDirection(Servo.Direction.REVERSE); //flip back goes in the opposite direction
        flipLeft.setPosition(0.3);
        flipRight.setPosition(0.32);

        gemBarShoulder = hardwareMap.servo.get("drop");
        gemBarWrist = hardwareMap.servo.get("kick");
        gemBarShoulder.setPosition(1);
        gemBarWrist.setPosition(0.8);
//
//        relicExtend = hardwareMap.dcMotor.get("relicExtend");
//        relicArm = hardwareMap.servo.get("relicArm");
//        relicClaw = hardwareMap.servo.get("relicClaw");
//        relicArm.setPosition(0);
//        relicClaw.setPosition(0);
//
//        colorSensorCollect = hardwareMap.colorSensor.get("colorSensorCollect");
        colorSensorJewel = hardwareMap.colorSensor.get("colorSensorJewel");

        wheelFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (encoders){
        }
        else{
            wheelFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        wheelFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wheelFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wheelBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // gyro setup
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        rev = hardwareMap.get(BNO055IMU.class, "imu");
        imu = new RevIMU(rev);
        imu.initialize();
        imu.setOffset(0);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameter = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameter.vuforiaLicenseKey = "AckoWtn/////AAAAGan7WAnq/0UVmQZG3sp7smBgRCNBnU1p+HmsTrC+W9TyxqaMlhFirDXglelvJCX4yBiO8oou6n7UWBfdRFbKHDqz0NIo5VcNHyhelmm0yK0vGKxoU0NZbQzjh5qVWnI/HRoFjM3JOq/LB/FTXgCcEaNGhXAqnz7nalixMeP8oRQlgX5nRVX4uE6w0K4yqIc5/FIDh1tn7PldiflmvNPhOW6FukPQD3d02wEnZB/JEchSSBzDbFA10XSgtYzXiweQI5tj+D5llLRrLh0mcWeouv55oSmya5RxUC26uEuO7bCAwyolWIuUr2Wh5oAG483nTD4vFhdjVMT7f0ovLO73C6xr2AXpNwen9IExRxBeosQ4";
        parameter.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameter);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();



        waitForStart();
    }

    protected void holdUp(double num)
    {
        timer.reset();
        while (timer.seconds()<num)
        {}
    }

    protected void imuTurn(double degree)
    {
        imu.setOffset(0);
        double num = 0.75, err = 0.5, pwr = 0;

        int count = 0;
        boolean flag = true;
        boolean dir_cw;

        if (degree>0)
        {
            dir_cw = true;
            wheelFL.setPower(num);
            wheelBL.setPower(num);
            wheelFR.setPower(-1*num);
            wheelBR.setPower(-1*num);
        }
        else
        {
            dir_cw = false;
            wheelFL.setPower(-1*num);
            wheelBL.setPower(-1*num);
            wheelFR.setPower(num);
            wheelBR.setPower(num);
        }

        while (flag)
        {
//            tickRatio = (FL.getCurrentPosition() - currentPos) / distanceTics;
//            speed = (-0.5 * (tickRatio * tickRatio) + 0.5);
            pwr = num-0.15*count;

//            double ratio = imu.getZAngle()/degree;
//            double pwr = (-1* num * (ratio * ratio) + num);
//            if (Math.abs(pwr)<0.2) {
//                if (pwr > 0)
//                    pwr = 0.2;
//                if (pwr < 0)
//                    pwr = -0.2;
//            }
//
//            telemetry.addData("Z angle",imu.getZAngle());
//            telemetry.addData("ratio",ratio);
//            telemetry.addData("power",pwr);
//            if (Math.abs(imu.getZAngle()-degree)<=err)
//                flag = false;
//            FL.setPower(pwr);
//            FR.setPower(-1*pwr);
//            BL.setPower(pwr);
//            BR.setPower(-1*pwr);

            if (Math.abs(imu.getZAngle()-degree)<err) {
                flag = false;
                wheelFL.setPower(0);
                wheelBL.setPower(0);
                wheelFR.setPower(0);
                wheelBR.setPower(0);
            }
            else if (dir_cw && imu.getZAngle()>degree)
            {
                wheelFL.setPower(-1*pwr);
                wheelBL.setPower(-1*pwr);
                wheelFR.setPower(pwr);
                wheelBR.setPower(pwr);
                count+=1;
                dir_cw = !dir_cw;
            }
            else if (!dir_cw && imu.getZAngle()<degree)
            {
                wheelFL.setPower(pwr);
                wheelBL.setPower(pwr);
                wheelFR.setPower(-1*pwr);
                wheelBR.setPower(-1*pwr);
                count+=1;
                dir_cw = !dir_cw;
            }
        }

        wheelFL.setPower(0);
        wheelBL.setPower(0);
        wheelFR.setPower(0);
        wheelBR.setPower(0);
        telemetry.addData("power",pwr);
    }

    protected void addTelemetry() {
    }

    protected void knockJewel(String jcolor)
    {
        boolean flag = false;
        boolean seeRed, knockRed;

        if (jcolor.equals("red"))
            knockRed = true;
        else
            knockRed = false;

        gemBarShoulder.setPosition(0);
        holdUp(.25);
        gemBarWrist.setPosition(0.4);

        holdUp(2);

        timer.reset();
        while (timer.seconds()<5 && !flag) {
            if ((colorSensorJewel.red() >= 3) || (colorSensorJewel.blue() >= 3) && !flag) {
                if (colorSensorJewel.red() > colorSensorJewel.blue())
                    seeRed=true;
                else
                    seeRed=false;
                flag = true;

                if (seeRed && knockRed)
                {
                    gemBarWrist.setPosition(1);
                    telemetry.addData("i see", "red");
                }
                else if (seeRed && !knockRed)
                {
                    gemBarWrist.setPosition(0);
                    telemetry.addData("i see", "red");
                }
                else if (!seeRed && knockRed)
                {
                    gemBarWrist.setPosition(0);
                    telemetry.addData("i see", "blue");
                }
                else
                {
                    gemBarWrist.setPosition(1);
                    telemetry.addData("i see", "blue");
                }
                holdUp(0.25);

                telemetry.update();
            }
        }

        gemBarWrist.setPosition(0.4);
        gemBarShoulder.setPosition(1);
        holdUp(.25);
        gemBarWrist.setPosition(0.8);
        holdUp(1);
    }

    protected void moveEncoders(double distanceInches, int dir){

        double speed = 0.5 * dir;
        int currentPos = wheelFL.getCurrentPosition();
        int distanceTics = (int)(distanceInches * CPI);
        double tickRatio;

        wheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFL.setTargetPosition(currentPos + distanceTics);
        wheelFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        wheelBR.setPower(speed);
        wheelBL.setPower(speed);
        wheelFL.setPower(speed);
        wheelFR.setPower(speed);


        while(wheelFL.isBusy() /*&& BL.isBusy() && BR.isBusy() && FL.isBusy()*/){
            tickRatio = ((double)wheelFL.getCurrentPosition() - (double)currentPos) / distanceTics;
            speed = dir * ((-0.5) * (tickRatio) + 0.5);
            if (dir > 0){
                if (speed < 0.15)
                    speed = 0.15;
            }
            if (dir < 0){
                if (speed > -0.15)
                    speed = -0.15;
            }
            telemetry.addData("" + speed, "");
            telemetry.addData("tickRatio" + tickRatio, "GetPos" + wheelFL.getCurrentPosition());
            telemetry.update();
            wheelBR.setPower(speed);
            wheelBL.setPower(speed);
            wheelFL.setPower(speed);
            wheelFR.setPower(speed);
        }
        wheelFR.setPower(0);
        wheelBR.setPower(0);
        wheelFL.setPower(0);
        wheelBL.setPower(0);
    }

    protected void harvest(boolean harvestMode, double power){
        if(!flipped){
            if(harvestMode){
                //Harvest Block
                intakeLeft.setPower(power);
                intakeRight.setPower(-power);
            }else{
                //Eject Block
                intakeRight.setPower(-power);
                intakeLeft.setPower(power);
            }
        }
    }

    protected void unloadBlocks(){
        // raise lift
        lift.setPower(1);
        lift.setPower(0);
        sleep(100);
        flipBlocks(true);
        sleep(500);
        flipBlocks(false);
        lift.setPower(-1);
        sleep(100);
        lift.setPower(0);
    }

    protected void flipBlocks(boolean flip){
        //TODO test to figure out servo min position and max position
        if(flip != flipped){
            if (flip == true){
                setFlipMotors(90);
                flipped = true;
            } else{
                setFlipMotors(0);
                flipped = false;
            }
        }
    }


    private void setFlipMotors(double pos){
//        flipBack.setPosition(pos);
//        flipForw.setPosition(1-pos);
    }


    protected void strafeEncoders(double distance){
        //if rotate is one then the left drive train's target will be set to negative
        double speed = 0.5;
        int dir = (int) Math.signum(distance);
        int currentPos = wheelBR.getCurrentPosition();
        int distanceTics = (int)(distance * CPI);
        double tickRatio;

        wheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBR.setTargetPosition(currentPos + distanceTics);
        wheelBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(wheelBR.isBusy() /*&& BL.isBusy() && BR.isBusy() && FL.isBusy()*/){
            tickRatio = ((double)wheelBR.getCurrentPosition() - (double)currentPos) / distanceTics;
            speed = ((-0.5) * (tickRatio) + 0.5);
            if (speed < 0.15)
                speed = 0.15;
            wheelFL.setPower(speed*dir);
            wheelFR.setPower(-speed*dir);
            wheelBL.setPower(-speed*dir);
            wheelBR.setPower(speed*dir);
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

    protected void wiggle (int driveDistance) {
        // travel 0.5 every time while wiggling
        int wiggleCount = (int)(driveDistance/0.5);
        int distanceTics = (int) (0.5 * CPI);
        int count = 0;

        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while ((wheelFR.getCurrentPosition() < driveDistance) && (wheelFL.getCurrentPosition() < driveDistance)) {
            if (count == 0) {
                for (int x = 1; x <= wiggleCount; x++) {
                    wheelBL.setTargetPosition(wheelBL.getCurrentPosition());
                    wheelFL.setTargetPosition(wheelFL.getCurrentPosition());
                    wheelBR.setTargetPosition(distanceTics * wiggleCount);
                    wheelFR.setTargetPosition(distanceTics * wiggleCount);
                }
            }
            else if (count == 1) {
                for (int x = 0; x < wiggleCount; x++) {
                    wheelBL.setTargetPosition(distanceTics * wiggleCount);
                    wheelFL.setTargetPosition(distanceTics * wiggleCount);
                    wheelBR.setTargetPosition(wheelBR.getCurrentPosition());
                    wheelFR.setTargetPosition(wheelFR.getCurrentPosition());
                }
            }

            while (wheelFR.isBusy() && wheelBL.isBusy() && wheelBR.isBusy() && wheelFL.isBusy()) {
                if (count == 0) {
                    wheelFR.setPower(0.3);
                    wheelBR.setPower(0.3);
                    wheelFL.setPower(0);
                    wheelBL.setPower(0);
                    count++;
                } else if (count == 1) {
                    wheelFR.setPower(0);
                    wheelFL.setPower(0.3);
                    wheelBR.setPower(0);
                    wheelBL.setPower(0.3);
                    count--;
                }
            }
        }
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

    public RelicRecoveryVuMark scanVuforia(){
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN){
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                parameter = new VuforiaLocalizer.Parameters();
                return vuMark;
            }
        }
        return vuMark;
    }


}
