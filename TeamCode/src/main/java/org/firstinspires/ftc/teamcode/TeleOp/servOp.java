package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.deHardwareMap;


@TeleOp(name = "TeleopComp")
public class servOp extends OpMode {

    deHardwareMap robot = new deHardwareMap();

    public double drive = 0d;
    public double strafe = 0d;
    public double twist = 0d;
    public double turretval = 0d;
    public double armyval = 0d;

    public boolean canItTurn = true;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        /*double drive = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double twist = gamepad1.right_stick_x;
*/
        drive = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        twist = gamepad1.right_stick_x;
        turretval = gamepad2.left_stick_x;
        armyval = gamepad2.right_stick_y;

        double[] speeds = {
                (- twist - strafe - drive),
                (- twist - strafe + drive),
                (- twist + strafe - drive),
                (- twist + strafe + drive)
        };
        /*String dataForTelemetry = String.valueOf(robot.daDistance.getDistance(DistanceUnit.CM));
        telemetry.addData(dataForTelemetry, dataForTelemetry);
        telemetry.update();*/
        robot.frontRight.setPower(speeds[0] * .65);
        robot.frontLeft.setPower(speeds[1] * .65);
        robot.backRight.setPower(speeds[2] * .65);
        robot.backLeft.setPower(speeds[3] * .65);

        robot.turret.setPower(turretval * .8);
        robot.army.setPower(armyval * .3);

        if (gamepad1.dpad_up) {
            robot.vClaw.setPosition(0);
        }
        else if (gamepad1.dpad_down) {
            robot.vClaw.setPosition(1);
        }
        if (gamepad1.right_trigger >= .3) {
            robot.hClaw.setPosition(0);
        } else if (gamepad1.left_trigger >= .3) {
            robot.hClaw.setPosition(1);
        }


        if (gamepad1.right_bumper) {
            robot.cClaw.setPosition(-1);
        } else if (gamepad1.left_bumper) {
            robot.cClaw.setPosition(0);
        }
    }

       /* if (gamepad2.a) {
            robot.armClaw.setPosition(0);
            canItTurn = false;
        } else if (gamepad2.b) {
            robot.armClaw.setPosition(0.5);
            canItTurn = true;
        } */
        //@Override
       // public void start() {
       // }

        //@Override


}
