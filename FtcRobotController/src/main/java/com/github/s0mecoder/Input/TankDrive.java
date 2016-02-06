package com.github.s0mecoder.Input;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

/**
 * Control your robot with tank dive
 */
public class TankDrive extends InputToHardware {


    private List<DcMotor> leftMotors, rightMotors;
    private Gamepad controller;
    private String leftStick, rightStick;

    /**
     * Control your wheels with tank drive.
     * @param leftMotors Robot's left side motors
     * @param rightMotors Robot's right side motors
     * @param controller Controller that is moving these
     * @param leftStick stick for left wheels
     * @param rightStick stick for right wheels
     */
    public TankDrive(List<DcMotor> leftMotors, List<DcMotor> rightMotors, Gamepad controller, String leftStick, String rightStick) {
        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;
        this.controller = controller;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }
    public void onUpdate()
    {
        float powerLeft, powerRight;
        float l = Util.square(controller.left_stick_y);
        float r = 0.5F * Util.square(controller.right_stick_x);
        powerLeft = Util.clamp(l, -1, 1);
        powerRight = Util.clamp(r, -1, 1);
        for (DcMotor m : leftMotors)
        {
            m.setDirection(DcMotor.Direction.FORWARD);
            m.setPower(powerLeft);
        }
        for (DcMotor m : rightMotors)
        {
            m.setDirection(DcMotor.Direction.FORWARD);
            m.setPower(powerRight);
        }
    }
    public void onStart()
    {

    }
    public void onStop()
    {

    }
}
