package org.firstinspires.ftc.teamcode.AutonomousCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.deHardwareMap;

@Autonomous(name = "1stAutonomousProgram", group = "Pushbot")
public class courtney extends LinearOpMode {

    deHardwareMap robot = new deHardwareMap();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontRight.setTargetPosition(7000);
        robot.frontLeft.setTargetPosition(-7000);
        robot.backRight.setTargetPosition(7000);
        robot.backLeft.setTargetPosition(-7000);

        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontRight.setPower(.8);
        robot.frontLeft.setPower(.8);
        robot.backRight.setPower(.8);
        robot.backLeft.setPower(.8);

        while (robot.backLeft.isBusy()||robot.backRight.isBusy()||robot.frontLeft.isBusy()||robot.frontRight.isBusy()) {
            //endless code
            String dataForTelemetry = (String.valueOf(robot.daDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData(dataForTelemetry, dataForTelemetry);
            telemetry.update();
        }
        robot.frontRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.backLeft.setPower(0);
    }
}
