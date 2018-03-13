package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.basictests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by poofs on 1/6/2018.
 */

@Autonomous(name = "Drive Time Test")
@Disabled
public class DriveTimeTest extends GeRMLinear {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
        driveStop(TIME, 1200, .3);
        imuTurn(.5, 90);
        sleep(2000);
        driveStop(TIME, 400, .3);
        imuTurn(.5,15);
//        sleep(7000);
//        driveStop(TIME, 1000, .3);
//        sleep(7000);
//        driveStop(TIME, 1000, .3);
//        sleep(7000);
        stop();
    }
}