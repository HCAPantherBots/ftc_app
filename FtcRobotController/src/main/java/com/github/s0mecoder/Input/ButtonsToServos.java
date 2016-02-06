package com.github.s0mecoder.Input;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

/**
 * Control servos with buttons
 */
public class ButtonsToServos extends InputToHardware{

    private List<Servo> servos;
    private Gamepad controller;
    private String buttonForward, buttonBackward;
    private Float step;
    private Boolean reversed;

    /**
     * Control servo groups with buttons. Reverse individual servos first if needed.
     * @param servos a list of servos
     * @param controller controller you want moving these
     * @param buttonForward button to move them all forward
     * @param buttonBackward button to move them all backward
     * @param step lower values are slower
     */
    public ButtonsToServos(List<Servo> servos, Gamepad controller, String buttonForward, String buttonBackward, Float step) {
        this.servos = servos;
        this.controller = controller;
        this.buttonForward = buttonForward;
        this.buttonBackward = buttonBackward;
        this.step = step;
    }

    public void onUpdate() {
        for (Servo s : servos) {
            float pos = (float) s.getPosition();
            int delta = 0;
            if (Util.getButtonPressed(controller, buttonForward)) {
                delta +=  this.step;
            }
            if (Util.getButtonPressed(controller, buttonBackward)) {
                delta -=  this.step;
            }
            s.setPosition(pos + delta);
        }
    }
    public void onStart()
    {

    }
    public void onStop()
    {

    }
}
