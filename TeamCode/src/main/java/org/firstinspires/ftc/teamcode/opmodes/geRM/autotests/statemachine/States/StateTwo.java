package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.States;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.AutoPullChain;
import org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.RobotInstance;
import org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.State;

/**
 * Created by Daniel on 1/30/18.
 */

public class StateTwo implements State {
    private RobotInstance instance;

    @Override
    public void pull(AutoPullChain wrapper) {
        //Do actions here
        this.instance = wrapper.instance;
        ElapsedTime runtime = new ElapsedTime();
        double initTime = runtime.milliseconds();

        while (runtime.milliseconds() - initTime < 1000){
            setDrive(-.5);
        }
        setDrive(0);

//        wrapper.setState(new StateOne());

    }
    public void setDrive(double p){
        instance.FL.setPower(p);
        instance.FR.setPower(p);
        instance.BL.setPower(p);
        instance.BR.setPower(p);
    }
}
