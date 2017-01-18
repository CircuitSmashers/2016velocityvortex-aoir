package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Sara on 11/5/2016.
 */
@Autonomous(name = "Beacon: Blue", group = "Beacon")
public class Color_Sensor_Auto extends LinearOpMode {

    Hardware robot = new Hardware();
    ColorSensor colorSensor;
    OpticalDistanceSensor opticalDistanceSensor;
    TouchSensor touchSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        float hsvValues[] = {0F, 0F, 0F};
        boolean bLedOn = false; //passive--active mode is true

        robot.init(hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("beacon");
        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("white_line");
        touchSensor = hardwareMap.touchSensor.get("touch");

        colorSensor.enableLed(bLedOn);

        //optical sensor detecting

        do {
            robot.leftMotor1.setPower(0.5);
            robot.rightMotor1.setPower(0.5);
        }
        while(!touchSensor.isPressed());
        robot.leftMotor1.setPower(0);
        robot.rightMotor1.setPower(0);

        if (colorSensor.blue() > 2) {
            robot.leftMotor1.setPower(0.5);
            robot.rightMotor1.setPower(0.5);
            sleep(500);
        } else if (colorSensor.red() > 2) {
            robot.leftMotor1.setPower(-0.5);
            robot.rightMotor1.setPower(-0.5);
            sleep(500);
            //add other motor movement here to push other side of beacon
        }
        robot.leftMotor1.setPower(0);
        robot.rightMotor1.setPower(0);

        while (opModeIsActive()) {
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
            telemetry.addData("blue", colorSensor.blue()); //send blue value back to the driver station
            telemetry.addData("red", colorSensor.red()); //send red back
            telemetry.addData("hue", hsvValues);
            telemetry.addData("Raw Light", opticalDistanceSensor.getRawLightDetected()); //fluctuates
            telemetry.addData("Normal Light", opticalDistanceSensor.getLightDetected()); //you will probably use this
            updateTelemetry(telemetry);

            //if(gamepad1.x) {
            //bLedOn = true;
            //colorSensor.enableLed(bLedOn);
            //if (gamepad1.y) {
            // bLedOn = false;
            // colorSensor.enableLed(bLedOn);
        }
    }
}



