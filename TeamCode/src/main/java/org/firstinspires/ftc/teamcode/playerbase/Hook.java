package org.firstinspires.ftc.teamcode.playerbase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.Motors;

public class Hook {
    Motors _motors;
    LinearOpMode _linearOpMode;

    public Hook(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _motors = new Motors(linearOpMode.hardwareMap, new String[] { Config.HOOK_MOTOR });
    }

    public void Execute() {
        if (_linearOpMode.gamepad2.x) {
            _motors.SetPower(1);
        }
        else {
            _motors.Stop();
        }
    }
}
