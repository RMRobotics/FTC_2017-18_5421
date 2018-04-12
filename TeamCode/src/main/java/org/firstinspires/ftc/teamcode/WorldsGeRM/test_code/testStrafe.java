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
        //FR.setDirection(DcMotor.Direction.REVERSE);


        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        simpleStrafeEncoders(24, 1);
        sleep(5000);
        simpleStrafeEncoders(24, -1);
        sleep(5000);
    }

    protected void strafeEncoders(double distanceInches, int dir) {
        //if rotate is one then the left drive train's target will be set to negative
        double speed = 1;
        int currentPos = FL.getCurrentPosition();
        int distanceTics = (int) (distanceInches * CPI);
        double tickRatio;
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FL.setTargetPosition(currentPos + distanceTics);

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
        double speed = 1;
        int currentPos = FL.getCurrentPosition();
        int distanceTics = (int) (distanceInches * CPI);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FL.setTargetPosition(currentPos + distanceTics*dir);

        while (FL.isBusy()) {
            telemetry.addData("" + speed, "");
            telemetry.update();

            FL.setPower(speed * dir);
            FR.setPower(-speed * dir);
            BL.setPower(-speed * dir);
            BR.setPower(speed * dir);
        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }

    public void holdUp(double time) {
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.seconds() < time) {
            telemetry.log().add("hold up");
            telemetry.clear();
        }
    }

}
