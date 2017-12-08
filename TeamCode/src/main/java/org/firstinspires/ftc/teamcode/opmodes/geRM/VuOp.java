package org.firstinspires.ftc.teamcode.opmodes.geRM;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;

/**
 * Created by Daniel on 11/21/17.
 */

public class VuOp extends GeRMLinear {
    @Override
    public void runOpMode() throws InterruptedException{
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);
        relicTrackables.activate();
        while (opModeIsActive()) {
            sleep(2000);
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        }
    }
}
