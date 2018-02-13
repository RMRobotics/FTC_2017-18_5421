package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Angela on 2/13/2018.
 */
@Autonomous (name = "runtimeprint")
public class runtimeprint extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        while (opModeIsActive()){
            telemetry.addData("runtime", runtime.milliseconds());
            telemetry.update();

        }
    }
}
