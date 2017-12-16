package org.firstinspires.ftc.teamcode.opmodes.geRM.autored;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.opmodes.geRM.GeRMAuto;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.CENTER;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;

/**
 * Created by tina on 11/16/17.
 */

@Autonomous(name = "RED1: Auto")
public class REDAuto1 extends GeRMAuto {

    @Override
    public void runOpMode() {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);
        setLift(200, .1);
//        int turnScale = -1;
//        jewelAndVuf(turnScale);
//
//        turn(CENTER, 0, .4);
//
        int turnVal2 = 90;

        int drive1 = 2000;
        int drive2 = 2150;
        int drive3 = 2300;
        int approachVal = 300;

        driveAndFaceBoxes(turnVal2, drive1, drive2, drive3, approachVal);
        ejectGlyph();


        int parkVal = 200;

        drivePark(parkVal);
    }
}