package org.firstinspires.ftc.teamcode.opmodes.geRM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;

/**
 * Created by Angela on 12/16/2017.
 */
@Autonomous(name = "colorSensorTest")
public class ColorSensorTest extends GeRMAuto {

    protected double jewelPos = 1;
    protected int colorTime = 200;
    protected int turnVal1 = 12;

    protected int ejectTime = 200;

    boolean detected;

    double initTime = runtime.milliseconds();

    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
        jewelArm.setPosition(0.8);
        while (opModeIsActive()) {
            try {
                int colorVal = colorSensorReader.read(0x04, 1)[0];
                telemetry.addData("color val:", colorVal);
                telemetry.update();
            } catch (NullPointerException e) {
                telemetry.addData("Null pointer- jewel detection", "");
            }
        }
    }
}