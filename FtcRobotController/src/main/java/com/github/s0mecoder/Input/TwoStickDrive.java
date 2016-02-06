package com.github.s0mecoder.Input;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

public class TwoStickDrive extends InputToHardware {

    private DcMotor leftMotors, rightMotors;
    private String throStick, steerStick, throAxis, steerAxis;

    /**
     * Control your robot with a throttle stick and steering stick
     * @param leftMotors Robot's left side motors
     * @param rightMotors Robot's right side motors
     */
    public TwoStickDrive(DcMotor leftMotors, DcMotor rightMotors) {
        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;
    }
    public void onUpdate(){}
    public void onUpdate(float lx, float ly, float rx, float ry)
    {
        float powerLeft, powerRight;
        float throttle = Util.square(ly);
        float steering = 0.5F * Util.square(rx);
        powerLeft = Util.clamp(throttle + steering, -1, 1);
        powerRight = Util.clamp(throttle - steering, -1, 1);

            leftMotors.setDirection(DcMotor.Direction.REVERSE);
            leftMotors.setPower(powerLeft);

            rightMotors.setDirection(DcMotor.Direction.FORWARD);
            rightMotors.setPower(powerRight);
    }
    public void onStart()
    {

    }
    public void onStop()
    {

    }
}