package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

/**
 * Created by ur mum xd on 3/22/2018.
 */

@Disabled
@Autonomous(name="dumpBotAutoBluePerp", group="dumpBotConfig")
public class dumpBotAutoBluePerp extends LinearOpMode {


    protected DcMotor wheelFL, wheelFR, wheelBL, wheelBR;

    protected DcMotor intakeLeft, intakeRight;

    protected Servo gemBarShoulder, gemBarWrist, pushBoy;

    protected ElapsedTime timer = new ElapsedTime();

    protected ColorSensor colorSensorJewel;

    protected Servo flipRight, flipLeft;

    static double CPI = (1120.0 * 0.66666)/(4.0 * Math.PI); //CALCULATIONS FOR THE COUNTS PER INCH

    protected BNO055IMU rev;
    protected IIMU imu;

    protected Orientation angles;
    protected Acceleration gravity;

    VuforiaLocalizer vuforia;


    @Override
    public void runOpMode() throws InterruptedException {

        wheelFL = hardwareMap.dcMotor.get("wheelFL");
        wheelFR = hardwareMap.dcMotor.get("wheelFR");
        wheelBL = hardwareMap.dcMotor.get("wheelBL");
        wheelBR = hardwareMap.dcMotor.get("wheelBR");
        wheelFL.setDirection(DcMotorSimple.Direction.REVERSE);
        wheelBL.setDirection(DcMotorSimple.Direction.REVERSE);


        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");

        flipLeft = hardwareMap.servo.get("flipLeft");
        flipRight = hardwareMap.servo.get("flipRight");

        pushBoy = hardwareMap.servo.get("pushBoy");

        colorSensorJewel = hardwareMap.colorSensor.get("colorSensorJewel");

        wheelFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        wheelFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wheelFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wheelBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rev = hardwareMap.get(BNO055IMU.class, "imu");
        imu = new RevIMU(rev);
        imu.initialize();
        imu.setOffset(0);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AckoWtn/////AAAAGan7WAnq/0UVmQZG3sp7smBgRCNBnU1p+HmsTrC+W9TyxqaMlhFirDXglelvJCX4yBiO8oou6n7UWBfdRFbKHDqz0NIo5VcNHyhelmm0yK0vGKxoU0NZbQzjh5qVWnI/HRoFjM3JOq/LB/FTXgCcEaNGhXAqnz7nalixMeP8oRQlgX5nRVX4uE6w0K4yqIc5/FIDh1tn7PldiflmvNPhOW6FukPQD3d02wEnZB/JEchSSBzDbFA10XSgtYzXiweQI5tj+D5llLRrLh0mcWeouv55oSmya5RxUC26uEuO7bCAwyolWIuUr2Wh5oAG483nTD4vFhdjVMT7f0ovLO73C6xr2AXpNwen9IExRxBeosQ4";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        telemetry.update();
        relicTrackables.activate();

        flipLeft.setPosition(0.75);
        flipRight.setPosition(0.77);

        waitForStart();







        gemBarShoulder = hardwareMap.servo.get("drop");
        gemBarWrist = hardwareMap.servo.get("kick");
        gemBarShoulder.setPosition(1);
        gemBarWrist.setPosition(0.8);



        //super.initialize(true, false);

        knockJewel("red");

//        setDrive(-1);
//
//        holdUp(0.1);
//
//        setDrive(0);

//        imuTurn(-5,0.4);



//        imuTurn(5,0.4);



        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN){
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
                parameters = new VuforiaLocalizer.Parameters();
            }
        }

        holdUp(0.7);

        setDrive(0.5);

        holdUp(1.05);

        setDrive(-0.3);

        holdUp(1.5);

        setDrive(0);

        moveEncoders(6, 1);

        if (vuMark == RelicRecoveryVuMark.LEFT){
            strafeEncoders(8, 1);
        }
        if (vuMark == RelicRecoveryVuMark.CENTER){
            strafeEncoders(15, 1);
        }
        if (vuMark == RelicRecoveryVuMark.RIGHT){
            strafeEncoders(20, 1);
        }

        moveEncoders(8, 1);

        intakeRight.setPower(1);
        intakeLeft.setPower(-1);

        moveEncoders(-3, -1);

        intakeRight.setPower(0);
        intakeLeft.setPower(0);

        imuTurn(145.0, 0.4);

        moveEncoders(12, 1);

        moveEncoders(-8, -1);

        imuTurn(135,0.5);

        moveEncoders(36,1);



    }

//    protected void glyphBoi()

    protected void holdUp(double num)
    {
        timer.reset();
        while (timer.seconds()<num)
        {}
    }

    protected void strafeEncoders(double distanceInches, int dir){

        double angle = imu.getZAngle();
        int currentPos = wheelFL.getCurrentPosition(), pos;
        int distanceTics = dir*(int)(distanceInches * CPI);
        double tickRatio;

        wheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFL.setTargetPosition(currentPos + distanceTics);
        wheelFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (dir == 1) {
            wheelBR.setPower(1);
            wheelBL.setPower(-1);
            wheelFL.setPower(1);
            wheelFR.setPower(-1);
        }

        if (dir == -1) {
            wheelBR.setPower(-1);
            wheelBL.setPower(1);
            wheelFL.setPower(-1);
            wheelFR.setPower(1);
        }


        while(wheelFL.isBusy() /*&& BL.isBusy() && BR.isBusy() && FL.isBusy()*/){
            if (Math.abs(-imu.getZAngle() - angle) >= 15){
                pos = wheelFL.getCurrentPosition();
                imuTurn(-(imu.getZAngle() - angle),0.4);
                wheelFL.setTargetPosition(wheelFL.getTargetPosition() + wheelFL.getCurrentPosition() - pos);
                if (dir == 1) {
                    wheelBR.setPower(1);
                    wheelBL.setPower(-1);
                    wheelFL.setPower(1);
                    wheelFR.setPower(-1);
                }

                if (dir == -1) {
                    wheelBR.setPower(-1);
                    wheelBL.setPower(1);
                    wheelFL.setPower(-1);
                    wheelFR.setPower(1);
                }
            }

        }
        wheelFR.setPower(0);
        wheelBR.setPower(0);
        wheelFL.setPower(0);
        wheelBL.setPower(0);
        double angleFinal = -(imu.getZAngle() - angle);
        imuTurn(angleFinal,0.4);
//        if (angleFinal > 0){
//            wheelFR.setPower(0.5);
//            wheelBR.setPower(0.5);
//            wheelFL.setPower(-0.5);
//            wheelBL.setPower(-0.5);
//            while (Math.abs(imu.getZAngle() - angle) > 1){ }
//            setDrive(0);
//        }
//        else{
//            wheelFR.setPower(-0.5);
//            wheelBR.setPower(-0.5);
//            wheelFL.setPower(0.5);
//            wheelBL.setPower(0.5);
//            while (Math.abs(imu.getZAngle() - angle) > 1){ }
//            setDrive(0);
//        }


    }



    protected void imuTurn(double degree, double num)
    {
        imu.setOffset(0);
        double err = 1.2, pwr = 0;

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
    }

    protected void knockJewel(String jcolor)
    {
        boolean flag = false;
        boolean seeRed, knockRed;

        if (jcolor.equals("red"))
            knockRed = true;
        else
            knockRed = false;

        gemBarShoulder.setPosition(0.5);
        gemBarWrist.setPosition(0.3);
        holdUp(2);
        holdUp(.25);
        gemBarShoulder.setPosition(0.12);

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
                    gemBarWrist.setPosition(-1);
                    telemetry.addData("i see", "red");
                }
                else if (!seeRed && knockRed)
                {
                    gemBarWrist.setPosition(-1);
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

    public void setDrive(double power){
        wheelFL.setPower(power);
        wheelBL.setPower(power);
        wheelFR.setPower(power);
        wheelBR.setPower(power);
    }



}
