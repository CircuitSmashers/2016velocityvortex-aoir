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
    /* (Servo Code)
    double          arm1Position     = robot.ARM1_HOME;                   // Servo safe position
    double          arm2Position    = robot.ARM2_HOME;                  // Servo safe position
    final double    ARM1_SPEED      = 0.01 ;                            // sets rate to move servo
    final double    ARM2_SPEED       = 0.01 ;                            // sets rate to move servo
    */

    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;
        double shooter;
        double particle;

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
            left = Range.clip(-gamepad1.left_stick_y, -0.7,0.7);
            right = Range.clip(-gamepad1.right_stick_y, -0.7,0.7);
            shooter = -gamepad2.right_stick_y;
            particle = -gamepad2.left_stick_y;
            robot.leftMotor1.setPower(left);
            robot.leftMotor2.setPower(left);
            robot.rightMotor1.setPower(right);
            robot.rightMotor2.setPower(right);

            // Use gamepad Y & A raise and lower the arm
            /* (Servo Code)
            if (gamepad2.a)
                arm1Position += ARM1_SPEED;
            else if (gamepad2.y)
                arm1Position -= ARM1_SPEED;
            */
            if (-gamepad2.right_stick_y > 0) { //Gamepad2 Right Stick Y is less than zero so shooterMotor spins counterclockwise relative to motor
                robot.shooterMotor.setPower(1);
            }
            else if (-gamepad2.right_stick_y < 0) { //Gamepad2 Right Stick Y is more than zero so shooterMotor spins clockwise relative to motor
                robot.shooterMotor.setPower(-1);
            }
            else { //Gamepad2 Right Stick Y is still so shooterMotor doesn't move
                robot.shooterMotor.setPower(0);
            }
            if (-gamepad2.left_stick_y > 0) { //Gamepad2 Left Stick Y is less than zero so particleMotor spins counterclockwise relative to motor
                robot.particleMotor.setPower(1);
            }
            else if (-gamepad2.left_stick_y < 0) { //Gamepad2 Left Stick Y is more than zero so particleMotor spins clockwise relative to motor
                robot.particleMotor.setPower(-1);
            }
            else { //Gamepad2 Left Stick Y is still so particleMotor doesn't move
                robot.particleMotor.setPower(0);
            }


            // Use gamepad X & B to open and close the claw
            /* (Servo Code)
           if (gamepad2.x)
                arm2Position += ARM2_SPEED;
            else if (gamepad2.b)
                arm2Position -= ARM2_SPEED;
            */

            // Move both servos to new position.
            /* (Servo Code)
            arm1Position  = Range.clip(arm1Position, robot.ARM1_MIN_RANGE, robot.ARM1_MAX_RANGE);
            robot.armServo1.setPosition(arm1Position);
            arm2Position = Range.clip(arm2Position, robot.ARM2_MIN_RANGE, robot.ARM2_MAX_RANGE);
            robot.armServo2.setPosition(arm2Position);
            */


            //Send telemetry message to signify robot running;
            /* (Servo Code)
            telemetry.addData("arm1",   "%.2f", arm1Position);
            telemetry.addData("arm2",  "%.2f", arm2Position);
            */
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("shooter", "%.2f", shooter);
            telemetry.addData("particle", "%.2f", particle);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}
