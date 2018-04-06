package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Kameron on 4/5/2018.
 */

@Autonomous(name="testVuforia", group="dumpBotConfig")
public class testVuforia extends org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(true, false);
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN){
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
                parameters = new VuforiaLocalizer.Parameters();
            }
        }
    }
}
