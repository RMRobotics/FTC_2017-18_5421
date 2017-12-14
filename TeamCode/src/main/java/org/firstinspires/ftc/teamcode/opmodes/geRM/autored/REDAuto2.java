package org.firstinspires.ftc.teamcode.opmodes.geRM.autored;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.geRM.GeRMAuto;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;

/**
 * Created by poofs on 11/28/2017.
 */

@Autonomous(name = "RED2: Auto")
public class REDAuto2 extends GeRMAuto{

    @Override
    public void runOpMode() {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);

        int turnScale = -1;
        jewelAndVuf(turnScale);

        turn(CENTER, -90, .4);

        int turnVal2 = 0;

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
