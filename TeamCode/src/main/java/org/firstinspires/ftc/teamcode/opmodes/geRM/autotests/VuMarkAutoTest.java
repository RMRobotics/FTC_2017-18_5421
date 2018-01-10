package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by poofs on 1/10/2018.
 */

@Autonomous(name = "VuMark Auto Test")
public class VuMarkAutoTest extends GeRMLinear {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
        // DRIVE FORWARD A LITTLE
        driveStop(TIME, 500, .15);

        // LOWER JEWEL ARM
        jewelArm.setPosition(.65);
        sleep(750);
        jewelArm.setPosition(1);

        boolean sensed = false;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
        // SLEEP
        initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 10000 && opModeIsActive()) {
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
        stop();
    }
}
