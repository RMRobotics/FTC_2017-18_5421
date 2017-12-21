package org.firstinspires.ftc.teamcode.opmodes.geRM.autotests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;
import org.firstinspires.ftc.teamcode.util.enums.Direction;

/**
 * Created by poofs on 12/21/2017.
 */

public class NavXTest extends GeRMLinear {

    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_WITHOUT_ENCODER, Direction.FORWARD);
        while (opModeIsActive()) {
            try {
                telemetry.addData("NavX Yaw", navx.getYaw());
                telemetry.update();
            } catch (NullPointerException e) {
                telemetry.addData("Null pointer for navx", "");
            }
        }
    }
}
