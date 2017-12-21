package org.firstinspires.ftc.teamcode.opmodes.geRM.autoblue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMAuto;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;

/**
 * Created by tina on 11/16/17.
 */

@Autonomous(name = "BLUE: Auto")
public class BLUEAuto1 extends GeRMAuto{

    @Override
    public void runOpMode() {
        super.initialize(Color.BLUE, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);

        int turnScale = 1;
        jewelAndVuf(turnScale);

        turn(CENTER, 0, .4);

        int turnVal2 = -90;

        int drive1 = 1200;
        int drive2 = 800;
        int drive3 = 400;
        int approachVal = 500;

        driveAndFaceBoxes(turnVal2, drive1, drive2, drive3, approachVal);

        ejectGlyph();

        int parkVal = 200;

        drivePark(parkVal);
    }
}