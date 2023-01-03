package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.deHardwareMap;


@TeleOp(name = "armpositionfinder")
public class armpositionfinder extends OpMode {
    deHardwareMap robot = new deHardwareMap();

    @Override
    public void init() {
        robot.init (hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData(">", robot.army.getCurrentPosition());
        telemetry.update();

    }
}
