package org.firstinspires.ftc.teamcode.opmodes.feRMilab;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.BACKWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
/**
 * Created by Angela on 11/19/2017.
 */

public class SenseOp extends GeRMLinear {


    @Override
    public void runOpMode() {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, BACKWARD);
        boolean detected = false;
        double initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 200 && opModeIsActive()) {
            if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 10) <= 1) {
                //0x04 is color number
                //10 is red
                left = Color.RED;
                right = Color.BLUE;
            } else if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 3) <= 1) {
                //3 is blue
                left = Color.BLUE;
                right = Color.RED;
            } else {
                left = Color.NEITHER;
            }

            // determine side with correct color
            if (left == Color.RED && right == Color.BLUE) {
                detected = true;
                jewel = RIGHT;
                telemetry.addData("SURE", jewel.toString());
            } else if (left == Color.BLUE && right == Color.RED) {
                detected = true;
                jewel = LEFT;
                telemetry.addData("SURE", jewel.toString());
            } else {
                if (left == Color.RED || right == Color.BLUE) {
                    detected = true;
                    jewel = RIGHT;
                } else if (left == Color.BLUE || right == Color.RED) {
                    detected = true;
                    jewel = LEFT;
                }
                // output probable side with correct color
                telemetry.addData("UNSURE:", jewel.toString());
            }
            if (detected == true) {
                jewelArm.setPosition(0.8);
            }
            telemetry.update();
        }
    }

    public void getGlyph() {
        glyphGrabber.setDirection(DcMotor.Direction.FORWARD);
        glyphGrabber.setPower(0.8);
    }

    public void pushGlyph() {
        glyphGrabber.setDirection(DcMotor.Direction.REVERSE);
        glyphGrabber.setPower(0.8);
    }

}