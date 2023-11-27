package org.firstinspires.ftc.teamcode.playerbase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.LimitSwitches;
import org.firstinspires.ftc.teamcode.base.Motors;

public class Drive {
    final double SLOW_SPEED = 0.5;
    double _govenor = 1.0;
    Motors _motor;
    LinearOpMode _linearOpMode;
    LimitSwitches _limitSwitches;

    public Drive(LinearOpMode linearOpMode, LimitSwitches limitSwitches) {
        _linearOpMode = linearOpMode;
        _limitSwitches = limitSwitches;
        _motor = new Motors(linearOpMode.hardwareMap, Config.DRIVE_MOTORS);

        for (DcMotor mtr: _motor.DcMotors) {
            mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void RunMecanum() {
        if (_linearOpMode.gamepad1.left_trigger != 0) {
            if (_govenor == 1.0) _govenor = SLOW_SPEED;
            else _govenor = 1.0;
            _linearOpMode.sleep(200);
        }

        double vertical = _linearOpMode.gamepad1.right_stick_y;
        double horizontal = -_linearOpMode.gamepad1.right_stick_x;
        double pivot = -_linearOpMode.gamepad1.left_stick_x;

        double flp = (pivot + vertical + horizontal) * _govenor;
        double frp = (-pivot + (vertical - horizontal)) * _govenor;
        double rlp = (pivot + (vertical - horizontal)) * _govenor;
        double rrp = (-pivot + vertical + horizontal) * _govenor;

        if ((flp > 0 || frp > 0 || rlp > 0 || rrp > 0) && _limitSwitches.IsPressed(Config.BASKET_SENSOR)) {
            _motor.Stop();
        } else {
            _motor.SetPower(new double[] { flp, frp, rlp, rrp });
        }

        _linearOpMode.telemetry.addData("Drive Mode", _govenor == 1.0 ? "Fast" : "Slow");
    }
}
