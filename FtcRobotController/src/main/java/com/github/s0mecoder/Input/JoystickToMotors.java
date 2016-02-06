package com.github.s0mecoder.Input;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

public class JoystickToMotors extends InputToHardware {

    private List<DcMotor> motors;
    private Gamepad controller;
    private String stick, axis;


    /**
     * Controller motors with a joystick
     * @param motors Motor group / list of motors
     * @param controller Driver or Operator controller?
     * @param stick Left or right joystick? (any string with l or r)
     * @param axis x or y axis of joystick?
     */
    public JoystickToMotors(List<DcMotor> motors, Gamepad controller, String stick, String axis) {
        this.motors = motors;
        this.controller = controller;
        this.stick = stick;
        this.axis = axis;
    }

    public void onUpdate()
    {
        Float value = controller.left_stick_x;
        for (DcMotor m : motors)
        {
            m.setPower(value);
        }
    }

    public void onStart()
    {

    }
    public void onStop()
    {

    }
}
