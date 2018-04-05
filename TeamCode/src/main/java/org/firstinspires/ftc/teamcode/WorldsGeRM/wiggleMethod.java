package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Angela on 4/5/2018.
 */

public class wiggleMethod extends dumpBotAutoSuper {
    @Override
    public void runOpMode() throws InterruptedException {
    }

        protected void wiggle (int driveDistance, double wiggleDistance) {
            int wiggleCount = (int)(driveDistance/wiggleDistance);
            int distanceTics = (int) (wiggleDistance * CPI);
            int count = 0;

            wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            while ((wheelFR.getCurrentPosition() < driveDistance) && (wheelFL.getCurrentPosition() < driveDistance)) {
                if (count == 0) {
                    for (int x = 1; x <= wiggleCount; x++) {
                        wheelBL.setTargetPosition((int)(wheelBL.getCurrentPosition()-0.1));
                        wheelFL.setTargetPosition((int)(wheelFL.getCurrentPosition()-0.1));
                        wheelBR.setTargetPosition(distanceTics * wiggleCount);
                        wheelFR.setTargetPosition(distanceTics * wiggleCount);
                    }
                }
                else if (count == 1) {
                    for (int x = 0; x < wiggleCount; x++) {
                        wheelBL.setTargetPosition(distanceTics * wiggleCount);
                        wheelFL.setTargetPosition(distanceTics * wiggleCount);
                        wheelBR.setTargetPosition((int)(wheelBR.getCurrentPosition()-0.1));
                        wheelFR.setTargetPosition((int)(wheelFR.getCurrentPosition()-0.1));
                    }
                }

                while (wheelFR.isBusy() && wheelBL.isBusy() && wheelBR.isBusy() && wheelFL.isBusy()) {
                    if (count == 0) {
                        wheelFR.setPower(0.3);
                        wheelBR.setPower(0.3);
                        wheelFL.setPower(-0.2);
                        wheelBL.setPower(-0.2);
                        count++;
                    } else if (count == 1) {
                        wheelFR.setPower(-0.2);
                        wheelFL.setPower(0.3);
                        wheelBR.setPower(-0.2);
                        wheelBL.setPower(0.3);
                        count--;
                    }
                }
            }
        }

    protected void moveEncoders(double distanceInches, int rotate){
        //if rotate is one then the left drive train's target will be set to negative
        rotate = -rotate;
        double speed = 0.5;
        int currentPos = wheelFL.getCurrentPosition();
        int distanceTics = (int)(distanceInches * CPI);
        double tickRatio;

        wheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wheelBL.setTargetPosition(currentPos + distanceTics);
        wheelFL.setTargetPosition(currentPos + distanceTics);
        wheelBR.setTargetPosition(currentPos + distanceTics);
        wheelFR.setTargetPosition(currentPos + distanceTics);

        wheelBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheelFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheelBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheelFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(wheelFR.isBusy() && wheelBL.isBusy() && wheelBR.isBusy() && wheelFL.isBusy()){
            tickRatio = (wheelFL.getCurrentPosition() - currentPos) / distanceTics;
            speed = (-0.5 * (tickRatio * tickRatio) + 0.5);
            wheelFR.setPower(speed*rotate);
            wheelFL.setPower(speed);
            wheelBR.setPower(speed*rotate);
            wheelBL.setPower(speed);
        }
    }

}
