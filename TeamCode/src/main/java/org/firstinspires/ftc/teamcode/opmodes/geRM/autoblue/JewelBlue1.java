package org.firstinspires.ftc.teamcode.opmodes.geRM.autoblue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by General on 1/5/2018.
 */
@Disabled
@Autonomous(name = "Jewel Blue 1")
public class JewelBlue1 extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.BLUE, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.85);
        sleep(750);
        jewelArm.setPosition(1);

//        setDrive(-.1);
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
        if ((colorSensor.red() >= 3) || (colorSensor.blue() >= 3)){
            if (colorSensor.red() > colorSensor.blue()){
                driveStop(TIME, 400, -0.3);
                distance = "CLOSER";
            } else if (colorSensor.blue() > colorSensor.red()){
                driveStop(TIME, 400, 0.3);
                distance = "FARTHER";
            }
        }
//
//        // RAISE JEWEL ARM
        jewelArm.setPosition(0);

        sleep(2000);

        // DRIVE FORWARD TO PARK
        switch (distance)
        {
            case "CLOSER":
                driveStop(TIME, 1000, -0.5);
                break;
            case "FARTHER":
                driveStop(TIME, 1300, -0.5);
                break;
            default:
                driveStop(TIME, 1150, -0.2);
        }

//        sleep(10000);
        // STOP
        stop();
    }
}