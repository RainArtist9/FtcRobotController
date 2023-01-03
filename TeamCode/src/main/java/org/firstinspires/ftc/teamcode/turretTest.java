package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "teleTurret")
public class turretTest extends OpMode {

// push
    public DcMotor frontRight= null;
    public DcMotor frontLeft= null;
    public DcMotor backRight= null;
    public DcMotor backLeft= null;
    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    }

    @Override
    public void loop() {
        double twist = gamepad1.left_stick_y;
        backRight.setPower(twist *.8);
    }
}
