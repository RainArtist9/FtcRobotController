package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareSoftware {

    HardwareMap hw = null;
    DcMotorEx FrontRight = null;
    DcMotorEx FrontLeft =null;
    DcMotorEx BackRight =null;
    DcMotorEx BackLeft = null;



    public void init(HardwareMap ahw){

        hw = ahw;

        FrontRight = hw.get(DcMotorEx.class, "frontRight");
        FrontLeft = hw.get(DcMotorEx.class, "frontLeft");
        BackRight = hw.get(DcMotorEx.class, "backRight");
        BackLeft = hw.get(DcMotorEx.class, "backLeft");

    }

    public DcMotorEx FrontRight(){
        return FrontRight;
    }
    public DcMotorEx FrontLeft(){
        return FrontLeft;
    }
    public DcMotorEx BackRight(){
        return BackRight;
    }
    public DcMotorEx BackLeft(){
        return BackLeft;
    }

}
