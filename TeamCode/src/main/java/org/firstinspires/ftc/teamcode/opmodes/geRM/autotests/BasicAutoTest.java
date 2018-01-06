package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;

/**
 * Created by General on 1/5/2018.
 */
@Autonomous(name = "Basic Auto Test")
public class BasicAutoTest extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
//        int turnVal2 = 90;
        turn(CENTER, 90, .3);
        stop();
    }
//    try {
//        initTime = runtime.milliseconds();
//        while (runtime.milliseconds() - initTime < 150 && opModeIsActive()) {
//            setDrive(0.1);
//        }
//        telemetry.addData("color red:", (colorSensor.red()>7));
//        telemetry.addData("color blue:", (colorSensor.blue()>7));
//        telemetry.update();
//        jewelArm.setPosition(0.2);
//        if (colorSensor.blue()>7)
//        {
//            turn(CENTER, -13, 0.2);
//        }
//        else
//        {
//            turn(CENTER, 13, 0.2);
//        }
//        turn(CENTER, 0, 0.2);
//        initTime = runtime.milliseconds();
//        while (runtime.milliseconds() - initTime < 300 && opModeIsActive()) {
//            setDrive(0.2);
//        }
//        turn(CENTER, 90, 0.2);
//        stop();
//
//    } catch (NullPointerException e) {
//        telemetry.addData("Null pointer- jewel detection", "");
//    }
}
