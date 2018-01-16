package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMAuto;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by Angela on 1/16/2018.
 */

@Autonomous(name = "Jewel Approach Auto Test")
public class JewelApproachAutoTest extends GeRMAuto {

    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.BLUE, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // DRIVE FORWARD A LITTLE
        driveStop(TIME, 500, .15);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.65);
        sleep(750);
        jewelArm.setPosition(1);

        // SLEEP
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 1000 && opModeIsActive()) {
            telemetry.addData("red value:", (colorSensor.red()));
            telemetry.addData("blue value:", (colorSensor.blue()));
            telemetry.update();
        }
        setDrive(0);

       String distance = "";

        // SENSE COLOR VALUE AND TURN ROBOT TO KNOCK JEWEL (sensor is facing left)
        detectJewel();

        // RAISE JEWEL ARM
        jewelArm.setPosition(0.21);

        sleep(5000);

        // DRIVE FORWARD TO PARK
        switch (distance)
        {
            case "CLOSER":
                drive(TIME, 500, -0.8);
                driveStop(TIME, 300, -0.3);
                break;
            case "FARTHER":
                drive(TIME, 1000, -0.8);
                driveStop(TIME, 300, -0.3);
                break;
            default:
                drive(TIME, 100, 0.3);
                drive(TIME, 1000, -0.8);
                driveStop(TIME, 300, -0.3);
        }

//        turn(CENTER, 90, 0.3);

        sleep(10000);
        // STOP
        stop();
    }
}