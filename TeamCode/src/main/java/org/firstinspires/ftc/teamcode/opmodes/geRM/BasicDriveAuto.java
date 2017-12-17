package org.firstinspires.ftc.teamcode.opmodes.geRM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;

/**
 * Created by poofs on 12/16/2017.
 */

@Autonomous(name = "Basic Drive Auto")
public class BasicDriveAuto extends GeRMAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 1000 && opModeIsActive()) {
            setDrive(0.5);
        }
        // driveStop(ENCODER, 2000, .1);

        while (runtime.milliseconds() - initTime < 1900 && opModeIsActive()) {
            //turn left
            setDrive(-0.5, 0.5, -0.5, 0.5);
        }
        while (runtime.milliseconds() - initTime < 2200 && opModeIsActive()) {
            setDrive(0.5);
        }
        //driveStop(ENCODER, 400, .1);
    }
}