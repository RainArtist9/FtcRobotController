package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "backTest", group = "Pushbot")
public class backTest extends OpMode {
    public DcMotor left = null;
    public DcMotor right = null;
    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "backLeft");
        right = hardwareMap.get(DcMotor.class, "backRight");
    }

    @Override
    public void loop() {

        left.setPower(gamepad1.left_stick_y);
        right.setPower(-gamepad1.right_stick_y);

    }
}
