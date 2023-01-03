package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareSoftware;


@TeleOp(name = "Carbo DT")
public class CarboDT extends OpMode{
    HardwareSoftware robot = new HardwareSoftware();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        //  double y = gamepad1.left_stick_y; // Remember, this is reversed!
       // double x = -gamepad1.right_stick_x;
        //double rx = gamepad1.left_stick_x;

        robot.FrontLeft().setPower(y + x + rx);
        robot.BackLeft().setPower(y - x + rx);
        robot.FrontRight().setPower(y - x - rx);
        robot.BackRight().setPower(y + x - rx);

    }

}
