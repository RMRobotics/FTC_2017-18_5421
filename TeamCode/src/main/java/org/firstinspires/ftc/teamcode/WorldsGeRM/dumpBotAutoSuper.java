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

    protected DcMotor wheelFL, wheelFR, wheelBL, wheelBR;

    protected DcMotor intakeLeft, intakeRight, lift;

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


    public void initialize(Boolean i){

        pushBoy = hardwareMap.servo.get("pushBoy");

        wheelFL = hardwareMap.dcMotor.get("wheelFL");
        wheelFR = hardwareMap.dcMotor.get("wheelFR");
        wheelBL = hardwareMap.dcMotor.get("wheelBL");
        wheelBR = hardwareMap.dcMotor.get("wheelBR");
        wheelFL.setDirection(DcMotorSimple.Direction.REVERSE);
        wheelBL.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("lift");

        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");

        flipLeft = hardwareMap.servo.get("flipLeft");
        flipRight = hardwareMap.servo.get("flipRight");
        flipLeft.setDirection(Servo.Direction.REVERSE);

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

        if (i)
            pushBoy.setPosition(0.1);
        else
            pushBoy.setPosition(1);

        flipLeft.setPosition(0.8);
        flipRight.setPosition(0.8);

        gemBarShoulder = hardwareMap.servo.get("drop");
        gemBarWrist = hardwareMap.servo.get("kick");
        gemBarShoulder.setPosition(0.93);
        gemBarWrist.setPosition(0.835);

        waitForStart();
    }

    public void moveOffRamp1(){

        setDrive(-0.25);

        holdUp(0.8);

        setDrive(0.2);

        holdUp(1.2);

        setDrive(0);
    }

    protected void flip(double l){

        flipLeft.setPosition(0.63);
        flipRight.setPosition(0.63);

        telemetry.update();

        holdUp(0.7);

        pushBoy.setPosition(0.9);

        lift.setPower(1);

        holdUp(0.7);

        lift.setPower(0);

        flipLeft.setPosition(0.13);
        flipRight.setPosition(0.13);

        holdUp(0.7);

        lift.setPower(-1);

        holdUp(0.7);

        lift.setPower(0);

        flipLeft.setPosition(0.83);
        flipRight.setPosition(0.83);
    }

    protected void print(String message, double time)
    {
        telemetry.log().add(message);
        holdUp(time);
    }

    protected void holdUp(double num)
    {
        timer.reset();
        while (timer.seconds()<num)
        {}
    }

    protected void shakeItOff(double time){
        moveEncoders(32.5,1);
        intakeRight.setPower(-1);
        intakeLeft.setPower(1);
        ElapsedTime timer1 = new ElapsedTime();
        timer1.reset();
        double angle = imu.getZAngle();
        boolean b = true;
        wheelFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(timer1.seconds() < time){
            //intakeLeft.setPower(0);
            //intakeRight.setPower(0);
            if (b) {
//                wheelBL.setPower(0.5);
//                wheelFL.setPower(0.5);
//                wheelBR.setPower(-0.5);
//                wheelFR.setPower(-0.5);
                setDrive(0.4);
                holdUp(0.5);
                setDrive(0);
//                wheelBL.setPower(-0.5);
//                wheelFL.setPower(-0.5);
//                wheelBR.setPower(0.5);
//                wheelFR.setPower(0.5);
                setDrive(0.4);
                holdUp(0.5);
                setDrive(0);
            }
            else
            {
//                wheelBL.setPower(-0.5);
//                wheelFL.setPower(-0.5);
//                wheelBR.setPower(0.5);
//                wheelFR.setPower(0.5);
//                setDrive(0.3);
//                holdUp(0.5);
//                setDrive(0);
//                wheelBL.setPower(0.5);
//                wheelFL.setPower(0.5);
//                wheelBR.setPower(-0.5);
//                wheelFR.setPower(-0.5);
//                setDrive(0.3);
//                holdUp(0.5);
//                setDrive(0);
            }
            //intakeRight.setPower(-1);
            //intakeLeft.setPower(1);
            //setDrive(0);
            b = !b;
        }
        pushBoy.setPosition(0);
        moveEncoders(-46.5,-1);
        intakeLeft.setPower(0);
        intakeRight.setPower(0);
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
                imuTurn(-(imu.getZAngle() - angle),0.3);
                //wheelFL.setTargetPosition(wheelFL.getTargetPosition() + wheelFL.getCurrentPosition() - pos);
                angle = imu.getZAngle();
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
        imuTurn(angleFinal,0.3);
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
        holdUp(0.3);
        gemBarWrist.setPosition(0.3);
        holdUp(0.5);
        gemBarShoulder.setPosition(0.12);

        holdUp(0.5);

        timer.reset();
        while (timer.seconds()<2 && !flag) {
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
        holdUp(0.8);
        gemBarWrist.setPosition(0.8);
        holdUp(0.8);
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

        print("ooga booga",0.5);

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
            wheelBR.setPower(speed);
            wheelBL.setPower(speed);
            wheelFL.setPower(speed);
            wheelFR.setPower(speed);
        }
        wheelFR.setPower(0);
        wheelBR.setPower(0);
        wheelFL.setPower(0);
        wheelBL.setPower(0);

        print("check 1.5",0.5);
    }

    public void setDrive(double power){
        wheelFL.setPower(power);
        wheelBL.setPower(power);
        wheelFR.setPower(power);
        wheelBR.setPower(power);
    }






}
