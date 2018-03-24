package org.firstinspires.ftc.teamcode.worldsCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by ur mum xd on 3/22/2018.
 */

@Autonomous(name="dumpBotAutoBluePerp", group="dumpBotConfig")
public class dumpBotAutoBluePerp extends  dumpBotAutoSuper{

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

        //retract arm






    }

    public void moveEncoders(int target, int rotate){
        //if rotate is one then the left drive train's target will be set to negative
        rotate = -rotate;
        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wheelBL.setTargetPosition();
    }


}
