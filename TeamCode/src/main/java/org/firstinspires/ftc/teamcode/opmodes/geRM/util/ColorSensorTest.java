package org.firstinspires.ftc.teamcode.opmodes.geRM.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMAuto;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.core.ColorValue;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static org.firstinspires.ftc.teamcode.util.enums.Color.RED;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;

/**
 * Created by Angela on 12/16/2017.
 */
@Autonomous(name = "colorSensorTest")
public class ColorSensorTest extends GeRMLinear {

    // hsvValues is an array that will hold the hue, saturation, and value information.
   // float hsvValues[] = {0F,0F,0F};

    // values is a reference to the hsvValues array.
   // final float values[] = hsvValues;

    public void runOpMode() throws InterruptedException {
        super.initialize(RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
//        try{
//        jewelArm.setPosition(0.85);}
//        catch (NullPointerException e){
//            telemetry.addData("Null Pointer", "setting jewel arm position");
//        }
        while (opModeIsActive()) {
            try {
//                ColorValue.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
                telemetry.addData("color red:", (colorSensor.red()));
                telemetry.addData("color blue:", (colorSensor.blue()));
                telemetry.update();
            } catch (NullPointerException e) {
                telemetry.addData("Null pointer- jewel detection", "");
            }
        }
    }
}