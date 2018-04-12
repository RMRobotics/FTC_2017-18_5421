package org.firstinspires.ftc.teamcode.WorldsGeRM.testing;

/**
 * Created by altra on 4/9/2018.
 */

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.WorldsGeRM.IIMU;

public class AutoFxns {

    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;
    private DcMotor relic;
    private Servo bigboi;
    private Servo smallboi;
    private Servo drop;
    private Servo kick;
    private ColorSensor color;
    private ElapsedTime time = new ElapsedTime();
    private IIMU imu;
    private Telemetry telemetry;

    public AutoFxns(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, DcMotor rel, Servo big, Servo smol, Servo dr, Servo ki, ColorSensor col, Telemetry tel, IIMU gyro)
    {
        this.FL = fl;
        this.FR = fr;
        this.BL = bl;
        this.BR = br;
        this.relic = rel;
        this.bigboi = big;
        this.smallboi = smol;
        this.drop = dr;
        this.kick = ki;
        this.color = col;
        this.telemetry = tel;
        this.imu = gyro;
    }

    public void stopMotion()
    {
        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
    }

    public void turn(double degree, double err)
    {

    }

    public void knockJewel(String jcolor)
    {
//        drop.setPosition(1);                 init values
//        kick.setPosition(0.8);
        boolean flag = false;
        boolean seeRed, knockRed;

        if (jcolor.equals("red"))
            knockRed = true;
        else
            knockRed = false;

        drop.setPosition(0);
        holdUp(.25);
        kick.setPosition(0.4);

        holdUp(2);

        time.reset();
        while (time.seconds()<5 && !flag) {
            if ((color.red() >= 3) || (color.blue() >= 3) && !flag) {
                if (color.red() > color.blue())
                    seeRed=true;
                else
                    seeRed=false;
                flag = true;

                if (seeRed && knockRed)
                {
                    kick.setPosition(1);
                    telemetry.addData("i see", "red");
                }
                else if (seeRed && !knockRed)
                {
                    kick.setPosition(0);
                    telemetry.addData("i see", "red");
                }
                else if (!seeRed && knockRed)
                {
                    kick.setPosition(0);
                    telemetry.addData("i see", "blue");
                }
                else
                {
                    kick.setPosition(1);
                    telemetry.addData("i see", "blue");
                }
                holdUp(0.25);

                telemetry.update();
            }
        }

        kick.setPosition(0.4);
        drop.setPosition(1);
        holdUp(.25);
        kick.setPosition(0.8);
        holdUp(1);

    }

    public void holdUp(double num)
    {
        time.reset();
        while (time.seconds()<num)
        {}
    }
}
