package org.firstinspires.ftc.teamcode.AutonomousCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.deHardwareMap;

@Disabled
@Autonomous(name = "bellaYasSlay", group = "Pushbot")
public class bellaYasSlay extends LinearOpMode {
    deHardwareMap robot = new deHardwareMap();
    public static int InchConvert(int inches) {
        // ticksPerinch is correct
        double ticksPerinch = 44.44444;
        return (int) (ticksPerinch * inches);
    } public static int degreeConvert(double degree) {
        double ticksPerDegree = 10.555555;
        return (int) (degree * ticksPerDegree);
    }
    public static int strafeInches(int inches) {
        //strafe is right
        double ticksPerStrafeInt = 52.631578;
        return (int) (inches * ticksPerStrafeInt);
        //stafe is dif than the normal
    }
    @Override
    public void runOpMode() throws InterruptedException {
        hardewareInit();
        waitForStart();
//        spin(50);
       moveForward(20);
        //strafeRight();
// 2000 is tiks
    }
    public void hardewareInit() {
        robot.init(hardwareMap);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void moveForward(int inches) {


        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontRight.setTargetPosition(InchConvert(inches));
        robot.frontLeft.setTargetPosition(-InchConvert(inches));
        robot.backRight.setTargetPosition(InchConvert(inches));
        robot.backLeft.setTargetPosition(-InchConvert(inches));

        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontRight.setPower(.4);
        robot.frontLeft.setPower(.4);
        robot.backRight.setPower(.4);
        robot.backLeft.setPower(.4);

        while (robot.backLeft.isBusy()||robot.backRight.isBusy()||robot.frontLeft.isBusy()||robot.frontRight.isBusy()) {
            //endless code
            /*String dataForTelemetry = (String.valueOf(robot.daDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData(dataForTelemetry, dataForTelemetry);
            telemetry.update();*/
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

        robot.frontRight.setPower(.4);
        robot.frontLeft.setPower(.4);
        robot.backRight.setPower(.4);
        robot.backLeft.setPower(.4);

        while (robot.backLeft.isBusy()||robot.backRight.isBusy()||robot.frontLeft.isBusy()||robot.frontRight.isBusy()) {
            //endless code
           /* String dataForTelemetry = (String.valueOf(robot.daDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData(dataForTelemetry, dataForTelemetry);
            telemetry.update();*/
        }
        robot.frontRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.backLeft.setPower(0);
    }
    public void strafeRight(int inches) {
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontRight.setTargetPosition(-strafeInches(inches));
        robot.frontLeft.setTargetPosition(-strafeInches(inches));
        robot.backRight.setTargetPosition(strafeInches(inches));
        robot.backLeft.setTargetPosition(strafeInches(inches));

        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontRight.setPower(.4);
        robot.frontLeft.setPower(.4);
        robot.backRight.setPower(.4);
        robot.backLeft.setPower(.4);

        while (robot.backLeft.isBusy()||robot.backRight.isBusy()||robot.frontLeft.isBusy()||robot.frontRight.isBusy()) {
            //endless code
            /*String dataForTelemetry = (String.valueOf(robot.daDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData(dataForTelemetry, dataForTelemetry);
            telemetry.update();*/
        }
        robot.frontRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.backLeft.setPower(0);
    }
}
