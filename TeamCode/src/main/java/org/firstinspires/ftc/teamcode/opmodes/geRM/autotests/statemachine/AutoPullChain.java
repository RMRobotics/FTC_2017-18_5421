package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine;

/**
 * Created by Daniel on 1/30/18.
 */

public class AutoPullChain {
    private State currentState;


    public AutoPullChain(State firstState) {
        currentState = firstState;
    }


    public void setState(State state){
        currentState = state;
    }

    public void pull(){
        currentState.pull(this);
    }

}
