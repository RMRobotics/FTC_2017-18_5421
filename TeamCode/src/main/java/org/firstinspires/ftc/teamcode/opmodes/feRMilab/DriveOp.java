package org.firstinspires.ftc.teamcode.opmodes.feRMilab;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.core.FeRMiLinear;
import org.firstinspires.ftc.teamcode.core.GeRMLinear;
import org.firstinspires.ftc.teamcode.util.enums.Color;

import static org.firstinspires.ftc.teamcode.util.enums.Direction.BACKWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.util.enums.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.ENCODER;
import static org.firstinspires.ftc.teamcode.util.enums.Drive.TIME;

/**
 * Created by tina on 11/16/17.
 */

@Autonomous(name = "RED: Auto")
public class DriveOp extends GeRMLinear{

    @Override
    public void runOpMode() {
        super.initialize(Color.RED, DcMotor.RunMode.RUN_USING_ENCODER, FORWARD);
//          Jewel
//        Drive forward to align arm
        drive(ENCODER, 1200, 0.5);
//        Turn servo to let down jewel arm
//        Detect color of jewel to the right of arm
//        Scan pictograph using Vuforia; store position
//        Turn robot depending on jewel color
//          Glyphs 45
//        Turn 90 towards cryptoboxes
//        Drive distance according to pictograph position (use predetermined distances or vuforia to detect three column
        RelicRecoveryVuMark vuMark = null;
        switch (vuMark){
            case LEFT:{ //largest amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
            case RIGHT: { //smallest amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
            case CENTER: { //middle amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
            case UNKNOWN:{ //middle amount
                drive(ENCODER, 1200, 0.5);
                break;
            }
        }
//        Turn 90 to face boxes
//        Turn compression wheels to push glyph into correct column
//          Park
//        Drive forward to park in safety triangle
        drive(ENCODER, 1200, 0.5);
    }
}
