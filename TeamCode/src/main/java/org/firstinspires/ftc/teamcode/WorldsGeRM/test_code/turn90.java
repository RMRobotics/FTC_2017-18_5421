package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.Locale;

/**
 * Created by Kameron on 4/5/2018.
 */

@Autonomous(name="turn90", group="dumpBotConfig")
public class turn90 extends org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper{
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(false, false);
//        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        float initialAngle = angles.thirdAngle;
        float finalAngle =  0;
        wheelBL.setPower(1);
        wheelBR.setPower(-1);
        wheelFL.setPower(1);
        wheelFR.setPower(-1);
        while (finalAngle - initialAngle < 90){
//            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            finalAngle = angles.thirdAngle;
        }
        wheelBL.setPower(0);
        wheelBR.setPower(0);
        wheelFL.setPower(0);
        wheelFR.setPower(0);

    }

}
