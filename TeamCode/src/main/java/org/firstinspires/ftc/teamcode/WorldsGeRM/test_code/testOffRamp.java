package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.WorldsGeRM.IIMU;
import org.firstinspires.ftc.teamcode.WorldsGeRM.RevIMU;

/**
 * Created by Kameron on 4/6/2018.
 */

@Autonomous(name="testOffRamp", group="twoMotor")
public class testOffRamp extends LinearOpMode
{
    //declarations
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;

    public void runOpMode()
    {
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotor.Direction.REVERSE);
        //BL.setDirection(DcMotor.Direction.REVERSE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        FL.setPower(1);
        BL.setPower(1);
        FR.setPower(1);
        BR.setPower(1);

        try {
            wait(1500);
        }
        catch (InterruptedException e){}

        FL.setPower(-0.4);
        BL.setPower(-0.4);
        FR.setPower(-0.4);
        BR.setPower(-0.4);

        FL.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        BR.setPower(0);

    }
}
