package org.firstinspires.ftc.teamcode.WorldsGeRM.testing;


import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.WorldsGeRM.IIMU;
import org.firstinspires.ftc.teamcode.WorldsGeRM.RevIMU;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.qualcomm.hardware.bosch.BNO055IMU;

/**
 * Created by altra on 4/2/2018.
 */

@TeleOp(name = "TestTele", group = "twoMotor")
public class testTele extends OpMode{

    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;
    private DcMotor lift;

    private Servo dumpL;
    private Servo dumpR;

    private Servo drop;
    private Servo kick;

    BNO055IMU rev;
    IIMU imu;



    public void init()
    {
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
//        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        lift = hardwareMap.dcMotor.get("lift");

        dumpL = hardwareMap.servo.get("dumpL");
        dumpR = hardwareMap.servo.get("dumpR");

        drop = hardwareMap.servo.get("drop");
        kick = hardwareMap.servo.get("kick");
        drop.setPosition(0);
        kick.setPosition(0);

        dumpL.setDirection(Servo.Direction.REVERSE);
        dumpL.setPosition(1);
        dumpR.setPosition(1);

        rev = hardwareMap.get(BNO055IMU.class, "imu");
        imu = new RevIMU(rev);
        imu.initialize();
        imu.setOffset(0);


        telemetry.log().add("We init to win it");
        telemetry.update();
    }

    public void loop(){

        double forward, strafe, rotate;
        forward = -gamepad1.right_stick_y;
        strafe = gamepad1.right_stick_x;
        rotate = gamepad1.left_stick_x;

        if (gamepad2.a)
            drop.setPosition(1);
        //up
        if (gamepad2.b)
            drop.setPosition(0);
        //down
        if (gamepad2.x)
            kick.setPosition(1);
        //left
        if (gamepad2.y)
            kick.setPosition(0);
        //right

        if (gamepad1.y)
        {
            dumpL.setPosition(0.5);
            dumpR.setPosition(0.36);
        }

        if (gamepad1.a)
        {
            dumpL.setPosition(1);
            dumpR.setPosition(0.86);
        }

        if (gamepad1.x)
        {
            dumpL.setPosition(0.75);
            dumpR.setPosition(0.75);
        }

        lift.setPower(gamepad2.right_stick_y);

//        if (gamepad2.dpad_down)
//        {
//            dumpL.setPosition(0.01+dumpL.getPosition());
//            dumpR.setPosition(0.01+dumpR.getPosition());
//        }
//        if (gamepad2.dpad_up)
//        {
//            dumpL.setPosition(dumpL.getPosition()-0.01);
//            dumpR.setPosition(dumpR.getPosition()-0.01);
//        }


        double max = 1;
        FL.setPower((forward + strafe + rotate) / max);
        FR.setPower((forward - strafe - rotate) / max);
        BL.setPower((forward - strafe + rotate) / max);
        BR.setPower((forward + strafe - rotate) / max);

        telemetry.addData("X angle", imu.getXAngle());
        telemetry.addData("dumpL pos", dumpL.getPosition());
        telemetry.addData("dumpR pos", dumpR.getPosition());
        telemetry.update();

    }

    String formatRate(float rate) {
        return String.format("%.3f", rate);
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format("%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}