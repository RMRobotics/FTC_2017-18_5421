package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

/**
 * Created by tina on 1/9/18.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

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
@Autonomous(name = "Vuforia Test")
public class VuforiaTest extends GeRMLinear {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // DRIVE FORWARD A LITTLE
        driveStop(TIME, 400, .15);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.65);
        sleep(750);
        jewelArm.setPosition(1);
        setDrive(-.1);
        // SLEEP
        initTime = runtime.milliseconds();
        while (opModeIsActive() && (relicMark == RelicRecoveryVuMark.UNKNOWN)) {
            telemetry.addData("red value:", (colorSensor.red()));
            telemetry.addData("blue value:", (colorSensor.blue()));


            telemetry.addData("VuMark", "%s found", relicMark);
            telemetry.update();
        }
        setDrive(0);

//        // SENSE COLOR VALUE AND TURN ROBOT TO KNOCK JEWEL (sensor is facing left)
//        if ((colorSensor.red() >= 5) || (colorSensor.blue() >= 5)){
//            if (colorSensor.red() > colorSensor.blue()){
//                driveStop(TIME, 400, 0.3);
//            } else if (colorSensor.blue() > colorSensor.red()){
//                driveStop(TIME, 300, -0.8);
//            }
//        }
//
//        // RAISE JEWEL ARM
//        jewelArm.setPosition(0.21);
//
//        sleep(1000);
//
//        // DRIVE FORWARD TO PARK
//        driveStop(TIME, 1000, .2);
//
//        sleep(10000);
//        // STOP
        stop();
    }
}
