package org.firstinspires.ftc.teamcode.playerbase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.LimitSwitches;
import org.firstinspires.ftc.teamcode.base.Motors;

public class Lift {
    Motors _motor;
    LimitSwitches _limitSwitches;
    LinearOpMode _linearOpMode;
    final int UPPER_LIMIT = 22700;

    public Lift(LinearOpMode linearOpMode, LimitSwitches limitSwitches) {
        _linearOpMode = linearOpMode;
        _limitSwitches = limitSwitches;
        _motor = new Motors(linearOpMode.hardwareMap, new String[] { Config.LIFT_MOTOR });
        _motor.DcMotors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void Execute() {
        if (-_linearOpMode.gamepad2.left_stick_y > 0 && _motor.GetCurrentPosition(Config.LIFT_MOTOR) < UPPER_LIMIT) {
            _motor.SetPower(-_linearOpMode.gamepad2.left_stick_y);
        } else if (-_linearOpMode.gamepad2.left_stick_y < 0 && !_limitSwitches.IsPressed(Config.LIFT_TOUCH_SENSOR)) {
            _motor.SetPower(-_linearOpMode.gamepad2.left_stick_y);
        } else {
            _motor.Stop();
        }

        _linearOpMode.telemetry.addData("Lift Is Pressed", _limitSwitches.IsPressed(Config.LIFT_TOUCH_SENSOR));
        _linearOpMode.telemetry.addData("Lift @ Upper Limit", _motor.GetCurrentPosition(Config.LIFT_MOTOR) < UPPER_LIMIT);
        _linearOpMode.telemetry.addData("Lift Pos", _motor.GetCurrentPosition(Config.LIFT_MOTOR));
    }
}
