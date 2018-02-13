package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;
import org.firstinspires.ftc.teamcode.util.enums.Direction;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;

/**
 * Created by Yardi on 2/13/2018.
 */

@Autonomous(name = "Turn By Time")
public class turnByTime extends GeRMLinear {

    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, Direction.FORWARD);
        while (opModeIsActive()) {
            try {
               turnByTime(LEFT, 0.3, 780);
            } catch (NullPointerException e) {
                telemetry.addData("Null pointer for navx", "");
                telemetry.update();
            }
        }
    }
}

