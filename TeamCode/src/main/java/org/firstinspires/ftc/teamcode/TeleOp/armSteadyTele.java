package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.deHardwareMap;

@TeleOp(name = "ArmStrong", group = "armstrong")
public class armSteadyTele extends OpMode {

    /* *
    This is the test method for Teleop that will keep the arm steady.
     */

    deHardwareMap robot = new deHardwareMap();

    public double drive = 0d;
    public double strafe = 0d;
    public double twist = 0d;
    public double turretval = 0d;
    public double armyval = 0d;

    public boolean canItTurn = true;
    public boolean buttonIsPressed = false;

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.army.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.army.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

        robot.turret.setPower(turretval * .5);
//        robot.army.setPower(armyval * .3);

        if (gamepad2.a) {
            robot.vClaw.setPosition(0);
            canItTurn = false;
            gamepad2.rumble(.5, .5, 300);
        } else if (gamepad2.b) {
            robot.vClaw.setPosition(0.5);
            canItTurn = true;
            gamepad2.rumble(.5, .5, 300);
        }

        if (gamepad2.dpad_up) {
            if (!buttonIsPressed) {
                robot.army.setTargetPosition(200);
                robot.army.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            buttonIsPressed = true;
        } else if (gamepad2.dpad_left || gamepad2.dpad_right) {
            if (!buttonIsPressed) {
                robot.army.setTargetPosition(100);
                robot.army.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            buttonIsPressed = true;
        } else if (gamepad2.dpad_down) {
            if (!buttonIsPressed) {
                robot.army.setTargetPosition(0);
                robot.army.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            buttonIsPressed = true;
        } else {
            buttonIsPressed = false;
        }


    }

}
