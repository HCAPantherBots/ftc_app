package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class redAuto extends OpMode{
    DcMotor lMotors, rMotors, aMotors, eMotors;

    @Override
    public void init() {
        rMotors = hardwareMap.dcMotor.get("right");
        lMotors = hardwareMap.dcMotor.get("left");
        aMotors = hardwareMap.dcMotor.get("arm");
        eMotors  = hardwareMap.dcMotor.get("elbow");
        lMotors.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
        super.start();
        super.resetStartTime();
        telemetry.addData(">", (int)getRuntime());
    }

    @Override
    public void loop() {
        double t1 = 10;

        if (getRuntime() < t1 && getRuntime() > 0) {
            drive(0, -0.3);
            telemetry.addData(">", "Step 1");
        }
        if (getRuntime() > t1) {
            drive(0, 0);
            telemetry.addData(">", "Done");
        }
    }

    private void drive(double turn, double power)
    {
        rMotors.setPower(Range.clip(power + (2 * turn), -1, 1));
        lMotors.setPower(Range.clip(power - (2 * turn), -1, 1));
    }
}
