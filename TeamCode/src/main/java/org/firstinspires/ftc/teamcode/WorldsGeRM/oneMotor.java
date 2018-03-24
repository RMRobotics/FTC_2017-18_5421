package org.firstinspires.ftc.teamcode.worldsCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Kameron on 3/15/2018.
 */
@Autonomous(name="testEncoders", group="oneMotor")
public class oneMotor extends LinearOpMode {

    protected DcMotor wheel1;
    protected DcMotor wheel2;
    protected DcMotor wheel3;


    public void runOpMode() {
        wheel1 = hardwareMap.dcMotor.get("wheel1");
        wheel2 = hardwareMap.dcMotor.get("wheel2");
        wheel3 = hardwareMap.dcMotor.get("wheel3");
        wheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        telemetry.addData("Reset Done", "hello");
        telemetry.update();
        wheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheel1.setTargetPosition(500);
        wheel2.setTargetPosition(500);
        wheel3.setTargetPosition(500);

        telemetry.addData("Run Mode Set", "hello");
        telemetry.update();
        wheel1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheel2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheel3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheel1.setPower(0.1);
        wheel2.setPower(0.1);
        wheel3.setPower(0.1);
        while (true) {
            telemetry.addData("Wheel 1 position at ", wheel1.getCurrentPosition());
            telemetry.addData("Wheel 2 position at ", wheel2.getCurrentPosition());
            telemetry.addData("Wheel 3 position at ", wheel3.getCurrentPosition());

            if(!wheel1.isBusy()){
                wheel1.setPower(0);
                telemetry.addData("Wheel 1 done", "");
            }
            if(!wheel2.isBusy()) {
                wheel2.setPower(0);
                telemetry.addData("Wheel 2 done", "");
            }
            if(!wheel3.isBusy()) {
                wheel3.setPower(0);
                telemetry.addData("Wheel 3 done", "");
            }
            telemetry.update();
            if (!wheel1.isBusy() && !wheel2.isBusy() && !wheel3.isBusy()) {
                //telemetry.addData("Program done hooray!","");
                wheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                wheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                wheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                stop();
            }
        }
    }
}