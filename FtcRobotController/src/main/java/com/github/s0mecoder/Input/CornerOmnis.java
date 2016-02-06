package com.github.s0mecoder.Input;

import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;

/**
 * Created by Collin on 10/22/15.
 */
public class CornerOmnis extends InputToHardware{

    ArrayList<DcMotor> motors1, motors2;

    public CornerOmnis(ArrayList<DcMotor> oppoMotors1, ArrayList<DcMotor> oppoMotors2) {
        this.motors1 = oppoMotors1;
        this.motors2 = oppoMotors2;

    }
    public void onUpdate(){}
    public void onUpdate(float lx, float ly, float rx, float ry)
    {
        double powerMotors1, powerMotors2;
        double moveMag = Math.sqrt(Math.pow(ly, 2) + Math.pow(lx, 2));
        double moveDir;
        if (lx == 0) {
            moveDir = ly >= 0 ? 0.7853981634 : -0.7853981634;
        } else {
            moveDir = Math.atan(ly / lx);
        }

        powerMotors1 = moveMag * Math.sin(moveDir);
        powerMotors2 = moveMag * Math.cos(moveDir);
        System.out.println(lx);
        for (DcMotor m : motors1)
        {
            m.setDirection(DcMotor.Direction.FORWARD);
            m.setPower(powerMotors1);
        }
        for (DcMotor m : motors2)
        {
            m.setDirection(DcMotor.Direction.FORWARD);
            m.setPower(powerMotors2);
        }
    }
    public void onStart()
    {

    }
    public void onStop()
    {

    }
}
