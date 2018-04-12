package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper;

@Autonomous(name = "testEncoderStrafe", group = "twoMotor")
public class testStrafe extends LinearOpMode {

    static double CPI = (1120.0 * 0.66666) / (4.0 * Math.PI);

    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;

    public void runOpMode() {
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotor.Direction.REVERSE);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        simpleStrafeEncoders(24, 1);
        sleep(5000);
        strafeEncoders(24, -1);
        sleep(5000);
    }

    protected void strafeEncoders(double distanceInches, int dir) {
        //if rotate is one then the left drive train's target will be set to negative
        double speed = .5;
        int distanceTics = (int) (distanceInches * CPI);
        double tickRatio;

        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int currentPos = FL.getCurrentPosition();
        FL.setTargetPosition(currentPos + distanceTics*dir);

        while (FL.isBusy()) {
            tickRatio = ((double) FL.getCurrentPosition() - (double) currentPos) / distanceTics;
            speed = ((-0.8) * (tickRatio) + 0.8);
            if (speed < 0.6)
                speed = 0.6;
            telemetry.addData("" + speed, "");
            telemetry.addData("tickRatio" + tickRatio, "GetPos" + FL.getCurrentPosition());
            telemetry.update();

            FL.setPower(speed * dir);
            FR.setPower(-speed * dir);
            BL.setPower(-speed * dir);
            BR.setPower(speed * dir);
        }
    }

    protected void simpleStrafeEncoders(double distanceInches, int dir) {
        //if rotate is one then the left drive train's target will be set to negative
        double speed = .7;
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int currentPos = FL.getCurrentPosition();
        int distanceTics = (int) (distanceInches * CPI);

        FL.setTargetPosition(currentPos + distanceTics*dir);

        strafeTelemetry();
        while (FL.isBusy()) {
            telemetry.addData("motor: ", speed);
            telemetry.update();

            FL.setPower(speed * dir);
            FR.setPower(-speed * dir);
            BL.setPower(-speed * dir);
            BR.setPower(speed * dir);
            strafeTelemetry();
        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
        strafeTelemetry();
    }

    public void strafeTelemetry(){
        telemetry.addData("FL curr pos:", FL.getCurrentPosition());
        telemetry.addData("FL target pos:", FL.getTargetPosition());
        telemetry.addData("8 Motor", FL.getPower() + " " + FR.getPower() + " " + BL.getPower() + " " + BR.getPower());
        telemetry.update();
    }
}
