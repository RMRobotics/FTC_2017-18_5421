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
    int degree = -90;
    BNO055IMU rev;
    IIMU imu;
    double pwr;

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

        telemetry.log().add("we init to winit");

        waitForStart();

        double num = 0.75, err = 0.5;

        int count = 0;
        boolean flag = true;
        boolean dir_cw;

        if (degree>0)
        {
            dir_cw = true;
            FL.setPower(num);
            BL.setPower(num);
            FR.setPower(-1*num);
            BR.setPower(-1*num);
        }
        else
        {
            dir_cw = false;
            FL.setPower(-1*num);
            BL.setPower(-1*num);
            FR.setPower(num);
            BR.setPower(num);
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
                FL.setPower(0);
                BL.setPower(0);
                FR.setPower(0);
                BR.setPower(0);
            }
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
                FL.setPower(pwr);
                BL.setPower(pwr);
                FR.setPower(-1*pwr);
                BR.setPower(-1*pwr);
                count+=1;
                dir_cw = !dir_cw;
            }
        }

        FL.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        BR.setPower(0);
        telemetry.addData("power",pwr);
    }
}
