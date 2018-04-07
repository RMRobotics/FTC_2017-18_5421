package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper;

@Autonomous(name="testEncodersSimple", group="twoMotor")
public class testEncoders extends LinearOpMode {

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

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        moveEncoders(12,0);
    }

    protected void moveEncoders(double distanceInches, int rotate){
        //if rotate is one then the left drive train's target will be set to negative
        rotate = -rotate;
        double speed = 0.5;
        int currentPos = FL.getCurrentPosition();
        int distanceTics = (int)(distanceInches * CPI);
        double tickRatio;

        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        BL.setTargetPosition(currentPos + distanceTics);
        FL.setTargetPosition(currentPos + distanceTics);
        BR.setTargetPosition(currentPos + distanceTics);
        FR.setTargetPosition(currentPos + distanceTics);

        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(FR.isBusy() && BL.isBusy() && BR.isBusy() && FL.isBusy()){
            tickRatio = (FL.getCurrentPosition() - currentPos) / distanceTics;
            speed = (-0.5 * (tickRatio * tickRatio) + 0.5);
            FR.setPower(speed*rotate);
            FL.setPower(speed);
            BR.setPower(speed*rotate);
            BL.setPower(speed);
        }
    }

}
