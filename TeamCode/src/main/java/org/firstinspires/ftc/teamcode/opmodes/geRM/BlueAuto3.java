package org.firstinspires.ftc.teamcode.opmodes.geRM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.enums.Color;
import org.firstinspires.ftc.teamcode.util.enums.Direction;

/**
 * Created by tina on 12/18/17.
 */
@Autonomous(name = "Blue Auto")
public class BlueAuto3 extends GeRMAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.BLUE, DcMotor.RunMode.RUN_WITHOUT_ENCODER, FORWARD);
    }


}
