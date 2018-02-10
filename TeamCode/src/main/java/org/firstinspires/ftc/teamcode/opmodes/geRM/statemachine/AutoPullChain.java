package org.firstinspires.ftc.teamcode.opmodes.geRM.statemachine;

/**
 * Created by Daniel on 1/30/18.
 */

public class AutoPullChain {
    private State currentState;
    public RobotInstance instance;
    //This constructor can be given either StateOne or StateTwo as a parameter, and sets it as the base state.
    public AutoPullChain(State firstState, RobotInstance instance) {
        currentState = firstState;
        this.instance = instance;
    }


    public void setState(State state){
        currentState = state;
        pull();
    }

    public void pull(){
        currentState.pull(this);
    }

}
