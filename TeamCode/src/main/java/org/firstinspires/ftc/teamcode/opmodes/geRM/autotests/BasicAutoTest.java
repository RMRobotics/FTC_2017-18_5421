package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

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
@Autonomous(name = "Basic Auto Test")
public class BasicAutoTest extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // DRIVE FORWARD A LITTLE
        driveStop(TIME, 400, .15);

        // LOWER JEWEL ARM
        jewelArm.setPosition(1);

        // SLEEP
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 2000 && opModeIsActive()) {
            telemetry.addData("red value:", (colorSensor.red()));
            telemetry.addData("blue value:", (colorSensor.blue()));
            telemetry.update();
        }

        // SENSE COLOR VALUE AND TURN ROBOT TO KNOCK JEWEL (sensor is facing left)
        if (colorSensor.red() > 7) // if red, turn right
        {
            turn(CENTER, -13, 0.2);
        }
        else if (colorSensor.blue() > 7) // if blue, turn left
        {
            turn(CENTER, 13, 0.2);
        }

        // RAISE JEWEL ARM
        jewelArm.setPosition(0.21);

//        // CORRECT TURN
//        turn(CENTER, 0, 0.2);
//
//        // DRIVE FORWARD TO PARK
//        driveStop(TIME, 1000, .2);

        // STOP
        stop();
    }
}
