package org.firstinspires.ftc.teamcode.WorldsGeRM.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by altra on 3/22/2018.
 */
@Disabled
@Autonomous(name="TestBot", group="twoMotor")
public class testBot extends LinearOpMode{

    //declarations
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;
    public final int arbitrary = 500;


    private Servo drop;
    private Servo kick;
    ElapsedTime timer = new ElapsedTime();
    protected ColorSensor colorSensor;

    public String wheelState(boolean flag)
    {
        if (flag)
            return "Not Done";
        return "Done";
    }

    public void holdUp(double time)
    {
        timer.reset();
        while (timer.seconds()<time)
        {
//            telemetry.addData("hold up","");
//            telemetry.update();
        }
//        telemetry.update();
    }

    public void runOpMode()
    {
        drop = hardwareMap.servo.get("drop");
        kick = hardwareMap.servo.get("kick");
        colorSensor = hardwareMap.colorSensor.get("color");
        drop.setPosition(1);
        kick.setPosition(0.8);
        AutoFxns autoboi = new AutoFxns(FL,FR,BL,BR,null,null,null,drop,kick,colorSensor,telemetry,null);

        waitForStart();

        autoboi.knockJewel("blue");

//        holdUp(.25);
//
//        drop.setPosition(0.1);
//        kick.setPosition(0.4);
//
//        holdUp(2);
//
//        timer.reset();
//        while (timer.seconds()<5 && !flag) {
//            if ((colorSensor.red() >= 3) || (colorSensor.blue() >= 3) && !flag) {
//                if (colorSensor.red() > colorSensor.blue()) {
//                    //turn right
//                    kick.setPosition(0);
//                    telemetry.addData("i see", "red");
//                    flag = true;
//                    holdUp(0.25);
//                    red = 1;
//                } else if (colorSensor.blue() > colorSensor.red()) {
//                    //turn left
//                    kick.setPosition(1);
//                    telemetry.addData("i see", "blue");
//                    flag = true;
//                    holdUp(0.25);
//                    red = -1;
//                }
//                telemetry.update();
//            }
//        }
//
//        telemetry.addData("red?",red);
//        telemetry.update();
//
//        kick.setPosition(0.4);
//        drop.setPosition(1);
//        holdUp(.25);
//        kick.setPosition(0.8);
//        holdUp(1);

    }
}
