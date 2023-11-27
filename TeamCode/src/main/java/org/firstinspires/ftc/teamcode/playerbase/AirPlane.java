package org.firstinspires.ftc.teamcode.playerbase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.Servos;

public class AirPlane {
    Servos _servos;
    LinearOpMode _linearOpMode;

    public AirPlane(LinearOpMode linearOpMode, Servos servos) {
        _servos = servos;
        _linearOpMode = linearOpMode;
    }

    public void Execute() {
        if (_linearOpMode.gamepad2.right_bumper) {
            _servos.MoveToLowerLimit(Config.AIRPLANE_SERVO);
        } else if (!_linearOpMode.gamepad2.right_bumper) {
            _servos.MoveToUpperLimit(Config.AIRPLANE_SERVO);
        }
    }
}
