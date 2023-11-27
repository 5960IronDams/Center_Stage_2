package org.firstinspires.ftc.teamcode.playerbase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.LimitSwitches;
import org.firstinspires.ftc.teamcode.base.Motors;
import org.firstinspires.ftc.teamcode.base.Servos;

public class Extender {
    Motors _motor;
    LimitSwitches _limitSwitches;
    Servos _servos;
    LinearOpMode _linearOpMode;
    final int UPPER_LIMIT = 4300;

    public Extender(LinearOpMode linearOpMode, LimitSwitches limitSwitches, Servos servos) {
        _linearOpMode = linearOpMode;
        _limitSwitches = limitSwitches;
        _servos = servos;
        _motor = new Motors(linearOpMode.hardwareMap, new String[] {Config.EXTENDER_MOTOR});
        _motor.DcMotors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void Execute() {
        if (_linearOpMode.gamepad2.right_stick_y < 0 && _motor.GetCurrentPosition(Config.EXTENDER_MOTOR) < UPPER_LIMIT && !_limitSwitches.IsPressed(Config.BASKET_SENSOR)) {
            _motor.SetPower (_linearOpMode.gamepad2.right_stick_y);
        } else if (_linearOpMode.gamepad2.right_stick_y > 0) {
            _motor.SetPower(_linearOpMode.gamepad2.right_stick_y);
        } else {
            _motor.Stop();
        }

        if (_limitSwitches.IsPressed(Config.BASKET_SENSOR)) {
            _servos.MoveToUpperLimit(Config.BASKET_SERVO);
        }

        if ((_linearOpMode.gamepad2.left_trigger != 0 || _limitSwitches.IsPressed(Config.BASKET_SENSOR))) {
            _servos.MoveToUpperLimit(Config.BASKET_SERVO);
        } else if (_linearOpMode.gamepad2.right_trigger != 0) {
            _servos.MoveToLowerLimit(Config.BASKET_SERVO);
        }
    }
}
