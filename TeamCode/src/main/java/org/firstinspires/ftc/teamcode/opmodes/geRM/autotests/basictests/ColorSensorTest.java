package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.basictests;

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
                telemetry.addData("color red:", (colorSensor.red()));
                telemetry.addData("color blue:", (colorSensor.blue()));
                telemetry.addData("argb val", (colorSensor.argb()));
                telemetry.update();
            } catch (NullPointerException e) {
                telemetry.addData("Null pointer- jewel detection", "");
            }
        }
    }
}