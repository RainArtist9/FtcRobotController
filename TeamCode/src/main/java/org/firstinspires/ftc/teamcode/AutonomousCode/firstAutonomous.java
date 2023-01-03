package org.firstinspires.ftc.teamcode.AutonomousCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.deHardwareMap;

@Disabled
@Autonomous(name = "HelloFirstAuto", group = "Pushbot")
public class firstAutonomous extends LinearOpMode {
    deHardwareMap robot = new deHardwareMap();
    public static int CentConvert(int centimeters) {
        // not yet set
        double ticksPericent = 17.3010380623;
        return (int) (ticksPericent * centimeters);
    } public static int degreeConvert(double degree) {
        double ticksPerDegree = 10.555555;
        return (int) (degree * ticksPerDegree);
    }
    public static int strafeCentimeter(int centimeters) {
        double ticksPerStrafeInt = 19.80198;
        return (int) (centimeters * ticksPerStrafeInt);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        hardewareInit();
        waitForStart();
        spin(50);
        moveForward(28);

    }
    public void hardewareInit() {
        robot.init(hardwareMap);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void moveForward(int centimetersTravelled) {


        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontRight.setTargetPosition(CentConvert(centimetersTravelled));
        robot.frontLeft.setTargetPosition(-CentConvert(centimetersTravelled));
        robot.backRight.setTargetPosition(CentConvert(centimetersTravelled));
        robot.backLeft.setTargetPosition(-CentConvert(centimetersTravelled));

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
    public void spin(double degree) {
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontRight.setTargetPosition(-degreeConvert(degree));
        robot.frontLeft.setTargetPosition(-degreeConvert(degree));
        robot.backRight.setTargetPosition(-degreeConvert(degree));
        robot.backLeft.setTargetPosition(-degreeConvert(degree));

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
    public void strafeRight(int centimeters) {
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontRight.setTargetPosition(-strafeCentimeter(centimeters));
        robot.frontLeft.setTargetPosition(-strafeCentimeter(centimeters));
        robot.backRight.setTargetPosition(strafeCentimeter(centimeters));
        robot.backLeft.setTargetPosition(strafeCentimeter(centimeters));

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
