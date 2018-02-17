package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.basictests;

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

@Autonomous(name = "Turn Test")
public class TurnTest extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);

        imuTurn(0.4,90);
        sleep(2000);
        imuTurn(0.4, -90);
        sleep(2000);
        imuTurn(0.4, 0);
        stop();
    }
}