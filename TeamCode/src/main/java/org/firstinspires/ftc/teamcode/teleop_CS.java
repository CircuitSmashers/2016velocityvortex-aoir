/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="The Cool TeleOp", group="Cool")

class teleop_CS extends LinearOpMode {

    /* Declare OpMode members. */
    private Hardware        robot           = new Hardware();              // Use hardware program

    private double          catcherPosition     = Hardware.MID_SERVO;                   // Servo safe position


    @Override
    public void runOpMode() throws InterruptedException {
        double left = 0;
        double right = 0;
        double winch = 0;
        double particle = 0;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Drivers!");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = Range.clip(-gamepad1.left_stick_y, -1,1);
            right = Range.clip(-gamepad1.right_stick_y, -1,1);
            particle = -gamepad2.right_stick_y;
            winch = gamepad2.left_stick_y;
            robot.leftMotor1.setPower(left);
            robot.rightMotor1.setPower(right);

            // Use gamepad Y & A raise and lower the catcher
            double CATCHER_SPEED = 0.05;
            if (gamepad2.y)
                catcherPosition += CATCHER_SPEED;
            else if (gamepad2.a)
                catcherPosition -= CATCHER_SPEED;


            //shooter motor, particle motor, and conveyor motor if/else if/else code
            if (gamepad2.left_trigger == 1) { //Gamepad2 left Trigger is equal to one so shooterMotor spins counterclockwise relative to motor
                robot.shooterMotor.setPower(1);
            }
            else if (gamepad2.right_trigger == 1) { //Gamepad2 Right Trigger is equal to one so shooterMotor spins clockwise relative to motor
                robot.shooterMotor.setPower(-1);
            }
            else { //Gamepad2 has no trigger movement so shooterMotor doesn't move
                robot.shooterMotor.setPower(0);
            }
            if (-gamepad2.right_stick_y > 0) { //Gamepad2 Right Stick Y is less than zero so particleMotor spins counterclockwise relative to motor
                robot.particleMotor.setPower(1);
            }
            else if (-gamepad2.right_stick_y < 0) { //Gamepad2 Right Stick Y is more than zero so particleMotor spins clockwise relative to motor
                robot.particleMotor.setPower(-1);
            }
            else { //Gamepad2 Right Stick Y is still so particleMotor doesn't move
                robot.particleMotor.setPower(0);
            }

            //winch and conveyor motors control code
            if (-gamepad2.left_stick_y > 0) { //Gamepad2 Left Stick Y is less than zero so winchMotor spins counterclockwise relative to motor
                robot.winchMotor.setPower(1);
            }
            else if (-gamepad2.left_stick_y < 0) { //Gamepad2 Left Stick Y is more than zero so winchMotor spins clockwise relative to motor
                robot.winchMotor.setPower(-1);
            }
            else { //Gamepad2 Right Stick Y is still so winchMotor doesn't move
                robot.winchMotor.setPower(0);
            }
            if (gamepad2.x) {
                robot.conveyorMotor.setPower(0.1);
            }
            else if (gamepad2.b) {
                robot.conveyorMotor.setPower(-0.5);
            }
        }


            // Move servo to new position.
            catcherPosition  = Range.clip(catcherPosition, Hardware.CATCHER_MIN_RANGE, Hardware.CATCHER_MAX_RANGE);
            robot.catcherServo.setPosition(catcherPosition);


            //Send telemetry message to signify robot running;

            //telemetry.addData("catcher arm",   "%.2f", catcherPosition);
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("winch", "%.2f", winch);
            telemetry.addData("particle", "%.2f", particle);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
            }
            }