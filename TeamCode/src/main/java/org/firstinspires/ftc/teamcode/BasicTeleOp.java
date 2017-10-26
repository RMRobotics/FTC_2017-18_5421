package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by tina on 10/24/17.
 */

public class BasicTeleOp extends OpMode {

    private ElapsedTime timer;
    private DcMotor wheelBR;
    private DcMotor wheelBL;
    private DcMotor wheelFL;
    private DcMotor wheelFR;
    private DcMotor glyphGrabber;
    private DcMotor liftR;
    private DcMotor liftL;
    private DcMotor relicGrabber;

    public void init(){
        wheelBR = hardwareMap.dcMotor.get("wheelBR");
        wheelBL = hardwareMap.dcMotor.get("wheelBL");
        wheelFL = hardwareMap.dcMotor.get("wheelFL");
        wheelFR = hardwareMap.dcMotor.get("wheelFR");
        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");
        glyphGrabber = hardwareMap.dcMotor.get("glyphGrabber");
        relicGrabber = hardwareMap.dcMotor.get("relicGrabber");
        wheelBR.setDirection(DcMotor.Direction.FORWARD);
        wheelBL.setDirection(DcMotor.Direction.FORWARD);
        wheelFL.setDirection(DcMotor.Direction.FORWARD);
        wheelFR.setDirection(DcMotor.Direction.FORWARD);
        wheelBR.setPower(0);
        wheelBL.setPower(0);
        wheelFL.setPower(0);
        wheelFR.setPower(0);


    }

    public void loop(){

        double forward = gamepad1.right_stick_y;
        double rotate  = gamepad1.left_stick_x;

        




    }



}
