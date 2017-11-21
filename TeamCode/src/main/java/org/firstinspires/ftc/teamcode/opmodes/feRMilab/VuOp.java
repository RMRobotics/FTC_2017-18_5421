package org.firstinspires.ftc.teamcode.opmodes.feRMilab;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;

/**
 * Created by Daniel on 11/21/17.
 */

public class VuOp extends GeRMLinear {
    @Override
    public void runOpMode() throws InterruptedException{

        VuforiaTrackables trackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable trackableTemplate = trackables.get(0);
        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(trackableTemplate);
            if(vuMark != RelicRecoveryVuMark.UNKNOWN){
                switch (vuMark){
                    case LEFT:{

                    }
                        break;
                    case RIGHT: {

                    }
                        break;
                    case CENTER: {

                    }
                        break;

                }
            }
        }
    }
}
