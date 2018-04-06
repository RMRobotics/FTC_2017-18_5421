<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/WorldsGeRM/oneMotor.java
package org.firstinspires.ftc.teamcode.WorldsGeRM;
=======
package org.firstinspires.ftc.teamcode.WorldsGeRM.test_code;
>>>>>>> 94d50408e0e9249450c460feecbbdc7f57abeded:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/WorldsGeRM/test_code/oneMotor.java

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Kameron on 3/15/2018.
 */
@Autonomous(name="testEncoders", group="oneMotor")
public class oneMotor extends LinearOpMode {

    protected DcMotor wheel1;
    protected DcMotor wheel2;
    protected DcMotor wheel3;

    public String wheelState(boolean flag)
    {
        if (flag)
            return "Not Done";
        return "Done";
    }


    public void runOpMode() {
        wheel1 = hardwareMap.dcMotor.get("wheel1");
        wheel2 = hardwareMap.dcMotor.get("wheel2");
        wheel3 = hardwareMap.dcMotor.get("wheel3");
        wheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        telemetry.addData("Reset Done", "hello");
        telemetry.update();
        wheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheel1.setTargetPosition(500);
        wheel2.setTargetPosition(500);
        wheel3.setTargetPosition(500);

        telemetry.addData("Run Mode Set", "hello");
        telemetry.update();
        wheel1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheel2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheel3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wheel1.setPower(0.1);
        wheel2.setPower(0.1);
        wheel3.setPower(0.1);
        boolean flag = true;
        while (flag) {
            telemetry.addData("Wheel 1 position at ", wheel1.getCurrentPosition());
            telemetry.addData("Wheel 2 position at ", wheel2.getCurrentPosition());
            telemetry.addData("Wheel 3 position at ", wheel3.getCurrentPosition());

            telemetry.addData("Wheel 1",   wheelState(wheel1.isBusy()));
            telemetry.addData("Wheel 2",  wheelState(wheel2.isBusy()));
            telemetry.addData("Wheel 3",    wheelState(wheel3.isBusy()));

            if(!wheel1.isBusy()){
                wheel1.setPower(0);
            }
            if(!wheel2.isBusy()) {
                wheel2.setPower(0);
            }
            if(!wheel3.isBusy()) {
                wheel3.setPower(0);
            }
            telemetry.update();
            if (!wheel1.isBusy() && !wheel2.isBusy() && !wheel3.isBusy()) {
                //telemetry.addData("Program done hooray!","");
                wheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                wheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                wheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                flag = !flag;
            }
        }
        while (true)
        {

        }
    }
}