package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

public class test extends OpMode {
    DcMotor lMotors, rMotors, aMotors, eMotors;
    public test() {}
    @Override
    public void init() {
        rMotors = hardwareMap.dcMotor.get("right");
        lMotors = hardwareMap.dcMotor.get("left");
        aMotors = hardwareMap.dcMotor.get("arm");
        eMotors  = hardwareMap.dcMotor.get("elbow");
        lMotors.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        rMotors.setPower(square(Range.clip(gamepad1.left_stick_y - (2 * gamepad1.right_stick_x), -1, 1)));
        lMotors.setPower(square(Range.clip(gamepad1.left_stick_y + (2 * gamepad1.right_stick_x), -1, 1)));
        aMotors.setPower(square(-gamepad2.left_stick_y));
        eMotors.setPower(square(-gamepad2.right_stick_y));
        telemetry.addData("frwd>",((rMotors.getPower()+lMotors.getPower())/2));
        telemetry.addData("turn>",(rMotors.getPower()-lMotors.getPower()));
    }
    @Override
    public void stop() {}
    public static double square(double n) {
        boolean pos = (n > 0), neg = (n < 0);
        return pos ? Math.pow(n, 2) : neg ? Math.pow(n, 2) * -1 : 0;
    }
}