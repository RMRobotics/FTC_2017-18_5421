package org.firstinspires.ftc.teamcode.WorldsGeRM.testing;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.WorldsGeRM.IIMU;
import org.firstinspires.ftc.teamcode.WorldsGeRM.RevIMU;

/**
 * Created by altra on 4/5/2018.
 */

@Autonomous(name="ImuTurn", group="twoMotor")
public class imuTurn extends LinearOpMode
{
    //declarations
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;
    int degree = 90;
    BNO055IMU rev;
    IIMU imu;

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
        rev = hardwareMap.get(BNO055IMU.class, "imu");
        imu = new RevIMU(rev);
        imu.initialize();
        imu.setOffset(0);

        waitForStart();

        double num = 1, err = 0.5;

        int count = 1;
        boolean flag = true;
        boolean dir_cw = true;
        FL.setPower(num);
        BL.setPower(num);
        FR.setPower(-1*num);
        BR.setPower(-1*num);
        while (flag)
        {
            double pwr = num/(Math.pow(2,count));
            telemetry.addData("Z angle",imu.getZAngle());
            if (Math.abs(imu.getZAngle()-degree)<=err)
                flag = false;
            else if (dir_cw && imu.getZAngle()>degree)
            {
                FL.setPower(-1*pwr);
                BL.setPower(-1*pwr);
                FR.setPower(pwr);
                BR.setPower(pwr);
                count+=1;
                dir_cw = !dir_cw;
            }
            else if (!dir_cw && imu.getZAngle()<degree)
            {
                FL.setPower(-1*(pwr));
                BL.setPower(-1*(pwr));
                FR.setPower(pwr);
                BR.setPower(pwr);
                count+=1;
                dir_cw = !dir_cw;
            }
        }

        FL.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        BR.setPower(0);
    }
}
