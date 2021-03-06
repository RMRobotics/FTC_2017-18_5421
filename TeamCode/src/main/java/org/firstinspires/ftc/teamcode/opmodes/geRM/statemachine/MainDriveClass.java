package org.firstinspires.ftc.teamcode.opmodes.geRM.statemachine;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmodes.geRM.statemachine.States.StateOne;

/**
 * Created by tina on 2/8/18.
 */

@Autonomous(name = "StateMachineDrive")
@Disabled
public class MainDriveClass extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        RobotInstance instance = new RobotInstance(hardwareMap.dcMotor.get("FL"), hardwareMap.dcMotor.get("FR"), hardwareMap.dcMotor.get("BL"),hardwareMap.dcMotor.get("BR"));
        waitForStart();

        AutoPullChain autoPullChain = new AutoPullChain(new StateOne(), instance);
        autoPullChain.pull();

    }


}