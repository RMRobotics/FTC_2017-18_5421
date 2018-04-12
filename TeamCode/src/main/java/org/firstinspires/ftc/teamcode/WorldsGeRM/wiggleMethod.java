//package org.firstinspires.ftc.teamcode.WorldsGeRM;
//
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
///**
// * Created by Angela on 4/11/2018.
// */
//@Autonomous (name = "wiggle", group="twoMotor")
//public class wiggleMethod extends LinearOpMode {
//    static double CPI = (1120.0 * 0.66666)/(4.0 * Math.PI);
//
//    public DcMotor FL;
//    public DcMotor FR;
//    public DcMotor BL;
//    public DcMotor BR;
//    protected BNO055IMU rev;
//    protected IIMU imu;
//
//    public void runOpMode(){
//
//        FL = hardwareMap.dcMotor.get("FL");
//        FR = hardwareMap.dcMotor.get("FR");
//        BL = hardwareMap.dcMotor.get("BL");
//        BR = hardwareMap.dcMotor.get("BR");
//        FL.setDirection(DcMotor.Direction.REVERSE);
//        //FR.setDirection(DcMotor.Direction.REVERSE);
//
//        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
////        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
////        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
////        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
////        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//
//        waitForStart();
//
//        wiggleEncoder(8);
//        wiggleTime(4);
//
//
//    }
//
//    protected void wiggleEncoder (int driveDistanceInches) {
//        // travel 1 in every time while wiggling
//        double wiggleDistance = 5 * CPI;
//        int wiggleCount = (int)(driveDistanceInches/wiggleDistance);
//        int count = 0;
//
//        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
////        try {
//        for (int x = 0; x < wiggleCount; x++) {
//            if (count == 0) {
//                FR.setTargetPosition(FR.getCurrentPosition() + (int) wiggleDistance);
//            } else if (count == 1) {
//                FL.setTargetPosition(FL.getCurrentPosition() + (int) wiggleDistance);
//            }
//        }
////        catch (NullPointerException e)
////            {
////                telemetry.addData("")
////            }
//
//            while (FR.isBusy() && FL.isBusy()) {
//                if (count == 0) {
//                    FR.setPower(0.5);
//                    BR.setPower(0.5);
//                    FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                    BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                    count++;
//                } else if (count == 1) {
//                    FL.setPower(0.5);
//                    BL.setPower(0.5);
//                    BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                    FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                    count--;
//                }
//            }
//            FR.setPower(0);
//            BR.setPower(0);
//            FL.setPower(0);
//            BL.setPower(0);
//        }
//    }
//
//    protected void wiggleTime(int wiggleReps)
//    {
//        int count = 0;
//        for (int x = 0; x < wiggleReps; x++) {
//            if (x % 2 == 0) {
//                FR.setPower(0.5);
//                BR.setPower(0.5);
//                sleep(1000);
//                FR.setPower(0);
//                BR.setPower(0);
//            }
//            else {
//                FL.setPower(0.5);
//                BL.setPower(0.5);
//                sleep(1000);
//                FL.setPower(0);
//                BL.setPower(0);
//            }
//        }
//        imuTurn(0);
//    }
//
//    protected void imuTurn(double degree)
//    {
//        imu.setOffset(0);
//        double num = 0.75, err = 0.5, pwr = 0;
//
//        int count = 0;
//        boolean flag = true;
//        boolean dir_cw;
//
//        if (degree>0)
//        {
//            dir_cw = true;
//            FL.setPower(num);
//            BL.setPower(num);
//            FR.setPower(-1*num);
//            BR.setPower(-1*num);
//        }
//        else
//        {
//            dir_cw = false;
//            FL.setPower(-1*num);
//            BL.setPower(-1*num);
//            FR.setPower(num);
//            BR.setPower(num);
//        }
//
//        while (flag)
//        {
//            if (Math.abs(imu.getZAngle()-degree)<err) {
//                flag = false;
//                FL.setPower(0);
//                BL.setPower(0);
//                FR.setPower(0);
//                BR.setPower(0);
//            }
//            else if (dir_cw && imu.getZAngle()>degree)
//            {
//                FL.setPower(-1*pwr);
//                BL.setPower(-1*pwr);
//                FR.setPower(pwr);
//                BR.setPower(pwr);
//                count+=1;
//                dir_cw = !dir_cw;
//            }
//            else if (!dir_cw && imu.getZAngle()<degree)
//            {
//                FL.setPower(pwr);
//                BL.setPower(pwr);
//                FR.setPower(-1*pwr);
//                BR.setPower(-1*pwr);
//                count+=1;
//                dir_cw = !dir_cw;
//            }
//        }
//
//        FL.setPower(0);
//        BL.setPower(0);
//        FR.setPower(0);
//        BR.setPower(0);
//        telemetry.addData("power",pwr);
//    }
//}
