package org.firstinspires.ftc.teamcode.opmodes.geRM.autored;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;


/**
 * Created by Angela on 12/14/2017.
 */

public class RedAuto1Jewel extends GeRMLinear {
    @Override

    public void runOpMode() {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);
//          Jewel
//          raise lift
        setLift(200, .1);

//        Drive forward to align arm
        driveStop(ENCODER, 1200, 0.5);
//        Turn servo to let down jewel arm
        jewelArm.setPosition(-0.93);
//        Detect jewel color
        boolean detected = false;
        double initTime = runtime.milliseconds();
        while (runtime.milliseconds() - initTime < 200 && opModeIsActive()) {
            if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 10) <= 1) {
                //0x04 is color number, 10 is red
                jewel = LEFT;
                detected = true;
            } else if (Math.abs(colorSensorReader.read(0x04, 1)[0] - 3) <= 1) {
                //3 is blue
                jewel = RIGHT;
                detected = true;
            } else {
                detected = false;
            }
            telemetry.addData("detected " + Boolean.toString(detected), jewel.toString());
            telemetry.update();
        }

        switch (jewel) {
            case LEFT: {
                turn(CENTER, -12, .4);
                break;
            }
            case RIGHT: {
                turn(CENTER, 12, .4);
                break;
            }
        }
        jewelArm.setPosition(0);
        turn(CENTER, 0, .4);
    }
}