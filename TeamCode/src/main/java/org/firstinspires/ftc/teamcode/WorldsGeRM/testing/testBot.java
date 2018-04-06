package org.firstinspires.ftc.teamcode.WorldsGeRM.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by altra on 3/22/2018.
 */

@Autonomous(name="TestBot", group="twoMotor")
public class testBot extends LinearOpMode{

    //declarations
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;
    public final int arbitrary = 500;


    public String wheelState(boolean flag)
    {
        if (flag)
            return "Not Done";
        return "Done";
    }

    public void runOpMode()
    {
        //motor initialization
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        telemetry.addData("Reset Done", "");
        telemetry.update();

        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setTargetPosition(arbitrary);
        FR.setTargetPosition(arbitrary);
        BL.setTargetPosition(arbitrary);
        BR.setTargetPosition(arbitrary);

        telemetry.addData("Run Mode Set", "");
        telemetry.update();
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setPower(0.1);
        FR.setPower(0.1);
        BL.setPower(0.1);
        BR.setPower(0.1);

        while (true) {
            telemetry.addData("FL position at ", FL.getCurrentPosition());
            telemetry.addData("FR position at ", FR.getCurrentPosition());
            telemetry.addData("BL position at ", BL.getCurrentPosition());
            telemetry.addData("BR position at ", BR.getCurrentPosition());

            telemetry.addData("Front Left: ",   wheelState(FL.isBusy()));
            telemetry.addData("Front Right: ",  wheelState(FR.isBusy()));
            telemetry.addData("Back Left: ",    wheelState(BL.isBusy()));
            telemetry.addData("Back Right: ",   wheelState(BR.isBusy()));
            telemetry.update();

            if (!FL.isBusy() && !FR.isBusy() && !BL.isBusy() && !BR.isBusy()) {
                telemetry.addData("All wheels done","");
                FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                telemetry.update();
                stop();
            }
        }

    }
}
