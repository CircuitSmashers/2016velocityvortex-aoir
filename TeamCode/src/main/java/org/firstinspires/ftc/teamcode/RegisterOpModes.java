package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
/*
import android.hardware.Sensor;
import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcontroller.external.samples.TemplateOpMode_Iterative;
*/


/*
 * Created by Sara on 9/27/2016.
 */

class RegisterOpModes
{

    @OpModeRegistrar
    public static void registerMyOpModes(OpModeManager manager) {

        manager.register("TeleOp_CS",       teleop_CS.class);

    }
}
    /**
     * This class demonstrates how to manually register opmodes.
     * <p/>
     * Note: It is NOT required to manually register OpModes, but this method is provided to support teams that
     * want to have centralized control over their opmode lists.
     * This sample is ALSO handy to use to selectively register the other sample opmodes.
     * Just copy/paste this sample to the TeamCode module and then uncomment the opmodes you wish to run.
     * <p/>
     * How it works:
     * The registerMyOpmodes method will be called by the system during the automatic registration process
     * You can register any of the opmodes in your App. by making manager.register() calls inside registerMyOpmodes();
     * <p/>
     * Note:
     * Even though you are performing a manual Registration, you should set the Annotations on your classes so they
     * can be placed into the correct Driver Station OpMode list...  eg:
     *
     * @Autonomous(name="DriveAndScoreRed", group ="Red")
     * or
     * @TeleOp(name="FastDrive", group ="A-Team")
     * <p/>
     * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
     * Then uncomment and copy the manager.register() call to register as many of your OpModes as you like.
     * You can even use it to temporarily register samples directly from the robotController/external/samples folder.
     */
