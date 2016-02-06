package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.HiTechnicNxtDcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Collin on 1/26/16.
 */
public class car extends OpMode{

    DcMotor left, right;
    Servo steer;

    public car() {}

    @Override
    public void init()
    {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        steer = hardwareMap.servo.get("steer");

        right.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop()
    {
        double throttle = gamepad1.right_trigger;
        double brake = gamepad1.left_trigger;
        double steering = 0.5 * (1 - gamepad1.left_stick_x);

            left.setPower(throttle-brake);
            right.setPower(throttle-brake);


        steer.setPosition(Range.clip(steering - 0.1, 0, 1));
    }
}
