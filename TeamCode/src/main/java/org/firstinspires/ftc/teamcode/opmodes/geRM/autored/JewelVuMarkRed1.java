package org.firstinspires.ftc.teamcode.opmodes.geRM.autored;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
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
@Autonomous(name = "Jewel VuMark Red 1")
public class JewelVuMarkRed1 extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.85);
        sleep(750);
        jewelArm.setPosition(1);

        boolean sensed = false;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
//        setDrive(-.1);
        // SLEEP
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 1000 && opModeIsActive()) {
            telemetry.addData("red value:", (colorSensor.red()));
            telemetry.addData("blue value:", (colorSensor.blue()));
            if (!sensed) {
                vuMark = RelicRecoveryVuMark.from(relicTemplate);
                if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                    sensed = true;
                    telemetry.addData("VuMark", "%s visible", vuMark);
                } else {
                    telemetry.addData("VuMark", "not visible");
                }
            } else {
                telemetry.addData("VuMark", "%s visible", vuMark);
            }
            telemetry.update();
        }
        int distance = 0;
        // SENSE COLOR VALUE AND TURN ROBOT TO KNOCK JEWEL (sensor is facing left)
        if ((colorSensor.red() >= 3) || (colorSensor.blue() >= 3)){
            if (colorSensor.red() > colorSensor.blue()){
                driveStop(TIME, 400, 0.3);
            } else if (colorSensor.blue() > colorSensor.red()){
                driveStop(TIME, 400, -0.3);
                distance -= 150;
            }
        }
        setDrive(0);
//        if (!sensed){
//            driveStop(TIME, 400, .3);
//            distance -= 400;
//            while (runtime.milliseconds() - initTime < 1000 && opModeIsActive()) {
//                if (!sensed) {
//                    vuMark = RelicRecoveryVuMark.from(relicTemplate);
//                    if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
//                        sensed = true;
//                        telemetry.addData("VuMark", "%s visible", vuMark);
//                    } else {
//                        telemetry.addData("VuMark", "not visible");
//                    }
//                } else {
//                    telemetry.addData("VuMark", "%s visible", vuMark);
//                }
//                telemetry.update();
//            }
//        }

//        // RAISE JEWEL ARM
        jewelArm.setPosition(0);

        sleep(2000);

        // DRIVE FORWARD TO PARK
        if (vuMark == RelicRecoveryVuMark.CENTER) {
            telemetry.addData("VuMark", "Center");
            telemetry.update();
            driveStop(TIME, 1250+distance, .3);
        } else if (vuMark == RelicRecoveryVuMark.LEFT) {
            telemetry.addData("VuMark", "Left");
            telemetry.update();
            driveStop(TIME, 1550+distance, .3);
        } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
            telemetry.addData("VuMark", "Right");
            telemetry.update();
            driveStop(TIME, 950+distance, .3);
        } else {
            telemetry.addData("VuMark", "Default");
            telemetry.update();
            driveStop(TIME, 1250+distance, .3);
        }

        imuTurn(.5, -90);

        sleep(2000);
        driveStop(TIME, 600, .3);
        glyphGrabber.setPower(-1);
        sleep(800);
        glyphGrabber.setPower(0);
        driveStop(TIME, 600, -.3);

//        turnByTime(RIGHT, 0.5, 1700);


        // STOP
        stop();
    }
}