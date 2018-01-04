package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMAuto;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;

/**
 * Created by Angela on 12/16/2017.
 */
@Autonomous(name = "colorSensorTest")
public class ColorSensorTest extends GeRMLinear {

    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
        jewelArm.setPosition(0.85);
        while (opModeIsActive()) {
            try {
                  initTime = runtime.milliseconds();
                while (runtime.milliseconds() - initTime < 150 && opModeIsActive()) {
                    setDrive(0.1);
                }
                telemetry.addData("color red:", (colorSensor.red()>7));
                telemetry.addData("color blue:", (colorSensor.blue()>7));
                telemetry.update();
                jewelArm.setPosition(0.2);
                if (colorSensor.blue()>7)
                {
                    turn(CENTER, -13, 0.2);
                }
                else
                {
                    turn(CENTER, 13, 0.2);
                }
                turn(CENTER, 0, 0.2);
                initTime = runtime.milliseconds();
                while (runtime.milliseconds() - initTime < 300 && opModeIsActive()) {
                    setDrive(0.2);
                }
                turn(CENTER, 90, 0.2);
                stop();

            } catch (NullPointerException e) {
                telemetry.addData("Null pointer- jewel detection", "");
            }
        }
    }
}