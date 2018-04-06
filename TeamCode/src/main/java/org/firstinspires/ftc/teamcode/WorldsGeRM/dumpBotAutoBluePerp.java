package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by ur mum xd on 3/22/2018.
 */

@Autonomous(name="dumpBotAutoBluePerp", group="dumpBotConfig")
public class dumpBotAutoBluePerp extends org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper {


    //wheelDiameterInches = 4;
    //ticksPerRotation = 1120;
    //gear ratio 1.5 to 1
    static double CPI = (1120.0 * 0.66666)/(4.0 * Math.PI); //CALCULATIONS FOR THE COUNTS PER INCH


    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(true, true);

        //scan the vuforia
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN){
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
                parameters = new VuforiaLocalizer.Parameters();
            }
        }

        //put the servo arm down slight, straighten arm for jewel pushing

        //color sensor stuff
        timer.reset();
        Boolean notDetected = true;
        while (notDetected && timer.seconds() < 5){
            if (colorSensorJewel.red() > 0){

            }
        }

        //retract arm





    }


<<<<<<< HEAD
        wheelBL.setTargetPosition(target);
    }
=======



>>>>>>> 94d50408e0e9249450c460feecbbdc7f57abeded


}
