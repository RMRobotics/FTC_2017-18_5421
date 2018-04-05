package org.firstinspires.ftc.teamcode.WorldsGeRM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angela on 4/3/2018.
 */

public class blockCollection extends dumpBotAutoSuper{

    String currPos = "";

    @Override
    public void runOpMode() throws InterruptedException {

        // position of the pictograph (L, C, R)
        String initPos = "";
        currPos = initPos;
        List<String> unvisited = new ArrayList<>();
        unvisited.add("LEFT");
        unvisited.add("RIGHT");
        unvisited.add("CENTER");
        unvisited.remove(initPos);

        // todo: find distance to changePos from left/right to center.
        int a = 300;



        for (int i = 0; i < 3; i++){
            // signs depend on red or blue team
            switch (i) {
                case 0:
                    changePos("CENTER");
                    break;
                case 1:
                    changePos("LEFT");
                    break;
                case 2:
                    changePos("RIGHT");
                    break;
            }

            // todo: find distance from cryptoboxes to block pit
            int b = 300;
            drive(-b);

            // start harvesting
            harvest(true, 1.0);

            // todo: find distance to drive while wiggling
            int c = 50;
            wiggle(c);

            changePos(unvisited.get(0));
            unvisited.remove(0);

            drive(b + c);

            flipBlocks();

            // todo: find distance to nudge blocks in
            int d = 40;
            drive(d);

            drive(-d);
        }

    }


    public void changePos(String finalPos){
        int intPos = 0;
        int currPosInt = 0;
        switch (finalPos){
            case "CENTER":
                intPos = 0;
                break;
            case "LEFT":
                intPos = -100;
                break;
            case "RIGHT":
                intPos = 100;
                break;
        }

        switch (currPos){
            case "CENTER":
                currPosInt = 0;
                break;
            case "LEFT":
                currPosInt = -100;
                break;
            case "RIGHT":
                currPosInt = 100;
                break;
        }

        strafe(finalPos - intPos);
        currPos = finalPos;
    }
}
