package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests.statemachine;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by tina on 2/8/18.
 */

public class RobotInstance {
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;

    public RobotInstance(DcMotor FL, DcMotor FR, DcMotor BL,DcMotor BR) {
        this.FL = FL;
        this.FR = FR;
        this.BL = BL;
        this.BR = BR;
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
    }
}
