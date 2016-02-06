package com.github.s0mecoder.Input;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Utilities for the Input classes
 */
public class Util {

    public static boolean getButtonPressed(Gamepad c, String button)
    {
        boolean p;
        String b = button.toLowerCase();
        p = b.equals("a") ? c.a : b.equals("b") ? c.b : b.equals("x") ? c.x : b.equals("y") && c.y;
        if (b.contains("dpad")) {
            if (b.contains("u")) p = c.dpad_up;
            if (b.contains("d")) p = c.dpad_down;
            if (b.contains("l")) p = c.dpad_left;
            if (b.contains("r")) p = c.dpad_right;
        }
        if (b.contains("guide")) p = c.guide;
        if (b.contains("start")) p = c.start;
        if (b.contains("back")) p = c.back;
        return p;
    }
    /**
     * Square a number and keep sign
     * @param n number
     * @return number squared
     */
    public static Float square(float n)
    {
        boolean pos = (n > 0), neg = (n < 0);
        return pos ? (float)Math.pow(n, 2) : neg ? (float)Math.pow(n, 2) * -1 : 0;
    }
    /**
     * Put numbers in there place by clamping them
     * @param n number
     * @param min lower clamp
     * @param max upper clamp
     * @return number clamped
     */
    public static Float clamp(float n, float min, float max)
    {
        return n > max ? max : n < min ? min : n;
    }
}
