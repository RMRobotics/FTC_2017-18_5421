package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;

/**
 * Created by General on 1/5/2018.
 */

@Autonomous(name = "Turn Time Test")
public class TurnTimeTest extends GeRMLinear{
    @Override
    public void runOpMode() throws InterruptedException {
        try {
            setDrive(.4, -.4);
        } catch (NullPointerException e){
            telemetry.addData("setdrive null", "");
            telemetry.update();
        }
        sleep(1000);
        setDrive(0);
    }
}
