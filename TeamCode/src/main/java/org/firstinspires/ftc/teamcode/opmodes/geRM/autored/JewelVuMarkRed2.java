package org.firstinspires.ftc.teamcode.opmodes.geRM.autored;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Color.BLUE;
import static org.firstinspires.ftc.teamcode.util.enums.Color.RED;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by General on 1/5/2018.
 */
@Disabled
@Autonomous(name = "Jewel VuMark Red 2")
public class JewelVuMarkRed2 extends GeRMLinear {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.85);
        sleep(750);
        jewelArm.setPosition(1);

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
//        setDrive(-.1);
        // SLEEP
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 1000 && opModeIsActive()) {
            telemetry.addData("red value:", (colorSensor.red()));
            telemetry.addData("blue value:", (colorSensor.blue()));

            telemetry.update();
        }
        setDrive(0);

        boolean colorSensed = false;
        Color jewel = null;
        // SENSE COLOR VALUE AND TURN ROBOT TO KNOCK JEWEL (sensor is facing left)
        if ((colorSensor.red() >= 3) || (colorSensor.blue() >= 3)) {
            if (colorSensor.red() > colorSensor.blue()) {
                jewel = RED;
                colorSensed = true;
                driveStop(TIME, 400, 0.3);

            } else if (colorSensor.blue() > colorSensor.red()) {
                jewel = BLUE;
                colorSensed = true;
                driveStop(TIME, 400, -0.3);
            }
        }
        jewelArm.setPosition(0);

        if (jewel == BLUE){
            driveStop(TIME, 500, .45);
        }
        if (!colorSensed) {
            driveStop(TIME, 400, .3);
        }

        boolean sensed = false;
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 2000 && opModeIsActive()) {
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

        driveStop(TIME, 850, .3);

        imuTurn(.5, 90);

        // DRIVE FORWARD TO PARK
        if (vuMark == RelicRecoveryVuMark.CENTER) {
            driveStop(TIME, 700, .3);
        } else if (vuMark == RelicRecoveryVuMark.LEFT) {
            driveStop(TIME, 900, .3);
        } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
            driveStop(TIME, 500, .3);
        } else {
            driveStop(TIME, 500, .3);
        }

        imuTurn(.5, 15);
        sleep(2000);

        driveStop(TIME, 400, 0.3);
        glyphGrabber.setPower(-1);
        sleep(1500);
        glyphGrabber.setPower(0);
        driveStop(TIME, 600, -0.3);

//        turnByTime(RIGHT, 0.5, 1700);

        // STOP
        stop();
    }
}
