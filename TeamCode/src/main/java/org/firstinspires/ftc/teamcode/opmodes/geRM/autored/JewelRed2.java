package org.firstinspires.ftc.teamcode.opmodes.geRM.autored;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by General on 1/5/2018.
 */
@Autonomous(name = "Jewel Red 2")
public class JewelRed2 extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // DRIVE FORWARD A LITTLE
        driveStop(TIME, 500, .15);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.65);
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

        // SENSE COLOR VALUE AND TURN ROBOT TO KNOCK JEWEL (sensor is facing left)
        String distance = "";
        if ((colorSensor.red() >= 5) || (colorSensor.blue() >= 5)){
            if (colorSensor.red() > colorSensor.blue()){
                driveStop(TIME, 400, 0.3);
                distance = "CLOSER";
            } else if (colorSensor.blue() > colorSensor.red()){
                driveStop(TIME, 300, -0.8);
                distance = "FARTHER";
            }
        }

        // RAISE JEWEL ARM
        jewelArm.setPosition(0.21);

        sleep(1000);

        // DRIVE FORWARD TO PARK
        switch (distance)
        {
            case "CLOSER":
                driveStop(TIME, 700, 0.2);
                break;
            case "FARTHER":
                driveStop(TIME, 1000, 0.2);
                break;
            default:
                driveStop(TIME, 850, 0.2);
        }

//        turn(CENTER, -90, 0.3);
//        driveStop(TIME, 200, 0.2);
//        turn(CENTER, 90, 0.2);


        sleep(10000);
        // STOP
        stop();
    }
}
