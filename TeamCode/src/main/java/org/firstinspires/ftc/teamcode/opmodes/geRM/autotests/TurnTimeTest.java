package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;

/**
 * Created by General on 1/5/2018.
 */

@Autonomous(name = "Turn Time Test")
public class TurnTimeTest extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        turn(CENTER, 90, .3);
        sleep(3000);
        turn(CENTER, 180, .3);
        sleep(3000);
        turn(CENTER, 90, .3);
        sleep(5000);

        navx.close();
        stop();
    }
}