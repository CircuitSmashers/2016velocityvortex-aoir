package org.firstinspires.ftc.teamcode;

/*
 * Created by Sara on 9/27/2016.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This is NOT an OpMode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a K9 robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left motor"
 * Motor channel:  Right drive motor:        "right motor"
 * Servo channel:  Servo to raise/lower arm: "arm"
 * Servo channel:  Servo to open/close claw: "claw"
 *
 * Note: the configuration of the servos is such that:
 *   As the arm servo approaches 0, the arm position moves up (away from the floor).
 *   As the claw servo approaches 0, the claw opens up (drops the game element).
 */
public class Hardware
{
    /* Public OpMode members. */
    // this is where you can add or remove motors
    DcMotor leftMotor1   = null;
    DcMotor rightMotor1  = null;
    DcMotor leftMotor2   = null;
    DcMotor rightMotor2  = null;
    DcMotor particleMotor = null;
    DcMotor shooterMotor = null;
    /* (Servo Code)
    Servo armServo1         = null;
    Servo armServo2         = null;
    */

    // this code determines the servo home positions and sets the minimum and maximum ranges
    /* (Servo Code)
    final static double ARM1_HOME = 0.5;
    final static double ARM2_HOME = 0.2;
    final static double ARM1_MIN_RANGE  = 0.20;
    final static double ARM1_MAX_RANGE  = 0.7;
    final static double ARM2_MIN_RANGE  = 0.20;
    final static double ARM2_MAX_RANGE  = 0.7;
    */
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Hardware() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map

        // Define and Initialize Motors
        leftMotor1   = ahwMap.dcMotor.get("Left Motor 1");
        rightMotor1  = ahwMap.dcMotor.get("Right Motor 1");
        leftMotor2   = ahwMap.dcMotor.get("Left Motor 2");
        rightMotor2  = ahwMap.dcMotor.get("Right Motor 2");
        particleMotor = ahwMap.dcMotor.get("Particle Motor");
        shooterMotor = ahwMap.dcMotor.get("Shooter Motor");
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        particleMotor.setDirection(DcMotor.Direction.REVERSE);


        // Set all motors to zero power
        leftMotor1.setPower(0);
        rightMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor2.setPower(0);
        particleMotor.setPower(0);
        shooterMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        /* (Servo Code)
        armServo1 = ahwMap.servo.get("Arm 1");
        armServo2 = ahwMap.servo.get("Arm 2");
        armServo1.setPosition(ARM1_HOME);
        armServo2.setPosition(ARM2_HOME);
        */
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    void waitForTick(long periodMs)  throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
