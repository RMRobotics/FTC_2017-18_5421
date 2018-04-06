package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Kameron on 4/5/2018.
 */

@Autonomous(name="offRampandBack", group="dumpBotConfig")
public class offRampandBack extends org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(false, false);
        wheelBL.setPower(1);
        wheelBR.setPower(1);
        wheelFL.setPower(1);
        wheelFR.setPower(1);
        wait(-1500);
        wheelBL.setPower(-0.2);
        wheelBR.setPower(-0.2);
        wheelFL.setPower(-0.2);
        wheelFR.setPower(-0.2);
        wait(-1500);
    }
}