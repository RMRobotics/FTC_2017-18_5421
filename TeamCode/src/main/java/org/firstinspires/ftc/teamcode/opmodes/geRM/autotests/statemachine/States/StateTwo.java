package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.States;

import org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.AutoPullChain;
import org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine.State;

/**
 * Created by Daniel on 1/30/18.
 */

public class StateTwo implements State {
    @Override
    public void pull(AutoPullChain wrapper) {
        //Do actions here





        wrapper.setState(new StateOne());
    }
}
