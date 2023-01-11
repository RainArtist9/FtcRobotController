package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.deHardwareMap;

@TeleOp(name = "ArmStrong", group = "armstrong")
public class armSteadyTele extends OpMode {

    deHardwareMap robot = new deHardwareMap();

    public double drive = 0d;
    public double strafe = 0d;
    public double twist = 0d;
    public double turretval = 0d;
    public double armyval = 0d;

    public double turretMult = .8;

    public boolean buttonActivate = true;
    public boolean canItTurn = true;
    public boolean listenToGamepad = true;

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
        armyval = -gamepad2.right_stick_y;

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

        robot.turret.setPower(turretval * turretMult);
        robot.army.setPower(armyval * .5);

        if (gamepad2.b) {
            robot.vClaw.setPosition(0);
            canItTurn = false;
        } else if (gamepad2.a) {
            robot.vClaw.setPosition(0.3);
            canItTurn = true;
        } else if (gamepad2.x) {
            robot.vClaw.setPosition(.7);
        }



        if (gamepad2.right_bumper) {
            robot.cClaw.setPosition(0);
            canItTurn = false;
        } else if (gamepad2.left_bumper) {
            robot.cClaw.setPosition(1);
            canItTurn = true;
        }




        if(gamepad2.y) {
            if (listenToGamepad) {
                if (buttonActivate) {
                    turretMult = .5;
                    buttonActivate = false;
                } else if (!buttonActivate) {
                    turretMult = .8;
                    buttonActivate = false;
                }
                listenToGamepad = false;
            }
        } else {
            listenToGamepad = true;
        }


        if (gamepad1.y) {
            robot.turretStop.setPosition(0);
        } else if (gamepad1.x) {
            robot.turretStop.setPosition(1);
        }

    }

}
