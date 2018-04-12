package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Angela on 4/11/2018.
 */
@Autonomous (name = "wiggle", group="twoMotor")
public class wiggleMethod extends LinearOpMode {
    static double CPI = (1120.0 * 0.66666)/(4.0 * Math.PI);

    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;

    public void runOpMode(){

        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotor.Direction.REVERSE);
        //FR.setDirection(DcMotor.Direction.REVERSE);


        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        waitForStart();

        wiggle(8);


    }

    protected void wiggle (int driveDistanceInches) {
        // travel 1 in every time while wiggling
        double wiggleDistance = 1 * CPI;
        int wiggleCount = (int)(driveDistanceInches/wiggleDistance);
        int distanceTics = (int) (driveDistanceInches * CPI);
        int count = 0;

        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        for (int x = 1; x <= wiggleCount; x++)
        {
            if (count == 0) {
//                BL.setTargetPosition(BL.getCurrentPosition());
//                FL.setTargetPosition(FL.getCurrentPosition());
                BR.setTargetPosition((int)wiggleDistance);
                FR.setTargetPosition((int)wiggleDistance);
            }
            else if (count == 1) {
                BL.setTargetPosition((int)wiggleDistance);
                FL.setTargetPosition((int)wiggleDistance);
//                BR.setTargetPosition(BR.getCurrentPosition());
//                FR.setTargetPosition(FR.getCurrentPosition());
            }

            while (FR.isBusy() && BL.isBusy() && BR.isBusy() && FL.isBusy()) {
                if (count == 0) {
                    FR.setPower(0.5);
                    BR.setPower(0.5);
                    FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                    count++;
                } else if (count == 1) {
                    FL.setPower(0.5);
                    BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                    FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    BL.setPower(0.5);
                    count--;
                }
            }
        }
    }
}
