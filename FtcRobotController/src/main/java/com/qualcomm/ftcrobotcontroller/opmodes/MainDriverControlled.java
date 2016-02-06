/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.github.s0mecoder.Input.*;
import com.github.s0mecoder.Output.*;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class MainDriverControlled extends OpMode {

	/* All code written up here is simply to create the variables we need later.
	 * Don't try to define what the variables are, you will have to do that in
	 * the start function below.
	 */

	/* InputToHardware will automatically bind input to a device movement.
	 * You can make a custom class based on the original for specific applications.
	 * We will put all objects into their lists in the start function.
	 */
	List<InputToHardware> devices;
	TwoStickDrive wheels, arm, elbow;

	/* Create your motors, servos, motor groups, and servo groups here.
	 * In the start function you will put these motors into the groups.
	 */
	ArrayList<DcMotor> wheelMotors, armM, elbowM;
	DcMotor leftMotors, rightMotors, armMotors, elbowMotors; //left front, left back, right front, right back, ...

	/* Create output devices for the values we care about then make a list to keep them in.
	 */

	/*Controller 1 and Controller 2
	 */
	Gamepad driver, operator;

	public MainDriverControlled() {}

	//Runs once on round start
	@Override
	public void init() {
		/* This is where you set the motors and servos you created above to physical motors.
		 * Base your wiring guide off of this.
		 */
		leftMotors = hardwareMap.dcMotor.get("left");
		rightMotors = hardwareMap.dcMotor.get("right");
		//armMotors = hardwareMap.dcMotor.get("arm");
		//elbowMotors = hardwareMap.dcMotor.get("elbow");


		//Reverse motor and servo direction if needed.

		/* TelemetryOutput will automatically output useful info from the motors and servos passed to it.
		 * Make new instances of the class with the outputs you created above.
		 */

		//Set our controllers equal to the real controllers.
		driver = gamepad1;
		operator = gamepad2;

		wheelMotors = new ArrayList<DcMotor>();
		armM = new ArrayList<DcMotor>();
		elbowM = new ArrayList<DcMotor>();
		devices = new ArrayList<InputToHardware>();
		//Add motors and servos to their respective groups.
		wheelMotors.add(leftMotors);
		wheelMotors.add(rightMotors);
		armM.add(armMotors);
		elbowM.add(elbowMotors);

		/* Set our devices to new instances of the Input Hardware Binding class.
		 * See the Input classes to find which one you want to use.
		 * If you don't like them you can add a new one to do exactly what you want.
		 */
		wheels = new TwoStickDrive(leftMotors, rightMotors);

		//Put our devices into the list we made for easy access.
		devices.add(wheels);


		//Run the onStart function for all devices
		for (InputToHardware d : devices) {
			d.onStart();
		}
	}

	//Method is looped during match.
	@Override
	public void loop() {
		//Update everything in the devices list.
		wheels.onUpdate(driver.left_stick_x, driver.left_stick_y, driver.right_stick_x, driver.right_stick_y);
	}

	@Override
	public void stop() {
		//run onStop for all devices
		//run onStop for all tele output.

	}
}

