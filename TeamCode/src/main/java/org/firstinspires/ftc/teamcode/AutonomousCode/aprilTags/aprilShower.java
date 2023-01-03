package org.firstinspires.ftc.teamcode.AutonomousCode.aprilTags;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.deHardwareMap;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name = "aprilShower", group = "Pushbot")
public class aprilShower extends LinearOpMode {
    public static deHardwareMap robot = new deHardwareMap();
    public static int InchConvert(int inches) {
        // ticksPerinch is correct
        double ticksPerinch = 44.44444;
        return (int) (ticksPerinch * inches);
    }
    public static int degreeConvert(double degree) {
        double ticksPerDegree = 10.555555;
        return (int) (degree * ticksPerDegree);
    }
    public static int strafeInches(int inches) {
        //strafe is right
        double ticksPerStrafeInt = 52.631578;
        return (int) (inches * ticksPerStrafeInt);
        //stafe is dif than the normal
    }


    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    static final int[] ID_TAG_OF_INTEREST = {5, 18, 29}; // Tag ID 18 from the 36h11 family

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "sauron"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }


        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
//                    if(tag.id == ID_TAG_OF_INTEREST)
//                    {
//                        tagOfInterest = tag;
//                        tagFound = true;
//                        break;
//                    }
                    switch (tag.id) {
                        case 5  : {tagOfInterest = tag; tagFound = true; break;}
                        case 18 : {tagOfInterest = tag; tagFound = true; break;}
                        case 29 : {tagOfInterest = tag; tagFound = true; break;}
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();

            hardewareInit();


        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        /* Actually do something useful */
        if(tagOfInterest == null)
        {
            /*
             * Insert your autonomous code here, presumably running some default configuration
             * since the tag was never sighted during INIT
             */
        }
        else
        {
            /*
             * Insert your autonomous code here, probably using the tag pose to decide your configuration.
             */

            // e.g.
            if(tagOfInterest.pose.x <= 20)
            {
                // do something
            }
            else if(tagOfInterest.pose.x >= 20 && tagOfInterest.pose.x <= 50)
            {
                // do something else
            }
            else if(tagOfInterest.pose.x >= 50)
            {
                // do something else
            }
        }
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));

    }
    public void hardewareInit() {
        robot.init(hardwareMap);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.army.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.army.setTargetPosition(robot.army.getCurrentPosition());
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
    public void clampClaw(double clampPosition) {
        robot.cClaw.setPosition(clampPosition);
    }
    public void ArmPosition(int position) {
        robot.army.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.army.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        switch (position) {
            case 0:{robot.army.setTargetPosition(0); break;}
            case 1:{robot.army.setTargetPosition(254); break;}
            case 2:{robot.army.setTargetPosition(422); break;}
            case 3:{robot.army.setTargetPosition(770); break;}
        }

        robot.army.setPower(.2);
        robot.army.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.army.isBusy()){

        }
        robot.army.setPower(0);
    }
    public void wrist(double wristPosition) {
        robot.vClaw.setPosition(wristPosition);
    }
    public static class ThreadStuff {
        public static boolean continueRunning = true;
        public static Thread runConstant = new Thread(() -> {
            while (continueRunning) {
                robot.army.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
        });
    }

}
