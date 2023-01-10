package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class deHardwareMap {
    public DcMotor frontRight  = null;
    public DcMotor frontLeft = null;
    public DcMotor backRight   = null;
    public DcMotor backLeft  = null;
    public DcMotor turret  = null;
    public DcMotor army  = null;
    public DistanceSensor daDistance = null;

    public Servo vClaw  = null;
    public Servo hClaw = null;
    public Servo cClaw = null;
    public Servo turretStop = null;

    HardwareMap hwmap = null;


    public deHardwareMap() {

    }
    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;
        frontRight = hwmap.get(DcMotor.class, "frontRight");
        frontLeft = hwmap.get(DcMotor.class, "frontLeft");
        backRight = hwmap.get(DcMotor.class, "backRight");
        backLeft = hwmap.get(DcMotor.class, "backLeft");
        turret = hwmap.get(DcMotor.class, "turret");
        army = hwmap.get(DcMotor.class, "army");



        vClaw = hwmap.get(Servo.class, "vClaw");
        cClaw = hwmap.get(Servo.class, "cClaw");
        hClaw = hwmap.get(Servo.class, "hClaw");
        turretStop = hwmap.get(Servo.class, "turretStop");
/*
        armTwist = hwmap.get(Servo.class, "armtwist");
*/

//        daDistance = hwmap.get(DistanceSensor.class, "distance");


        army.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        turret.setPower(0);
        army.setPower(0);
    }

}
