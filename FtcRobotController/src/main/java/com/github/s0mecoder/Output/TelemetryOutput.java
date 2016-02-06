package com.github.s0mecoder.Output;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

public class TelemetryOutput {

    private Telemetry t = new Telemetry();
    private String msg;
    private String type;
    private Servo s;
    private DcMotor m;

    public TelemetryOutput(DcMotor m) {
        this.m = m;
        setM(m);
        this.type = "m";
    }
    public TelemetryOutput(Servo s) {
        this.s = s;
        setS(s);
        this.type = "s";
    }

    public void onUpdate()
    {
        if (type.equals("s")) {
            setS(this.s);
        }
        if (type.equals("m")) {
            setM(this.m);
        }
        t.addData(this.type + ">", msg);
    }

    public void onStop()
    {
        t.addData(this.type + ">", "Stopped");
    }

    private void setS(Servo s) {
        this.msg = s.getController() + " - " + s.getPortNumber() + " - " + s.getPosition();
    }
    private void setM(DcMotor m) {
        this.msg = m.getController() + " - " + m.getPortNumber() + " - " + m.getDirection() + " - " + m.getPower();

    }
}
