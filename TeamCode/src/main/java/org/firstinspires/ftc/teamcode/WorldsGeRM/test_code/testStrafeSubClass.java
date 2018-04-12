package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.WorldsGeRM.dumpBotAutoSuper;

@Autonomous(name="testEncoderStrafeSubClass", group="twoMotor")
public class testStrafeSubClass extends dumpBotAutoSuper {
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(false, true);

        waitForStart();
        strafeEncoders(24);
        strafeEncoders(-24);
        sleep(5000);
    }
}
