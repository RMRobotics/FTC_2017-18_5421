package org.firstinspires.ftc.teamcode.WorldsGeRM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by ur mum xd on 3/22/2018.
 */

@Autonomous(name="dumpBotAutoBlueParFinal", group="dumpBotConfig")
public class dumpBotAutoBlueParFinal extends dumpBotAutoSuper {

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AckoWtn/////AAAAGan7WAnq/0UVmQZG3sp7smBgRCNBnU1p+HmsTrC+W9TyxqaMlhFirDXglelvJCX4yBiO8oou6n7UWBfdRFbKHDqz0NIo5VcNHyhelmm0yK0vGKxoU0NZbQzjh5qVWnI/HRoFjM3JOq/LB/FTXgCcEaNGhXAqnz7nalixMeP8oRQlgX5nRVX4uE6w0K4yqIc5/FIDh1tn7PldiflmvNPhOW6FukPQD3d02wEnZB/JEchSSBzDbFA10XSgtYzXiweQI5tj+D5llLRrLh0mcWeouv55oSmya5RxUC26uEuO7bCAwyolWIuUr2Wh5oAG483nTD4vFhdjVMT7f0ovLO73C6xr2AXpNwen9IExRxBeosQ4";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        telemetry.update();
        relicTrackables.activate();

        initialize(true);


        knockJewel("red");

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN){
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                parameters = new VuforiaLocalizer.Parameters();
            }
        }


        if (vuMark == RelicRecoveryVuMark.LEFT){
            moveEncoders(20, 1);
        }
        if (vuMark == RelicRecoveryVuMark.CENTER){
            moveEncoders(30, 1);
        }
        if (vuMark == RelicRecoveryVuMark.RIGHT){
            moveEncoders(40, 1);
        }

        wheelFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imuTurn(95, 0.4);

        setDrive(-0.5);

        if (vuMark == RelicRecoveryVuMark.LEFT){
            holdUp(0.18);
        }
        else {
            holdUp(0.12);
        }

        setDrive(0);

        ////

        flip(0.2);

        shakeItOff(1);

        flip(0.55);

        setDrive(0.5);

        holdUp(0.25);

        setDrive(0);


    }

}

