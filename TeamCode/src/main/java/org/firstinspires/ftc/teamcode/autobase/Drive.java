package org.firstinspires.ftc.teamcode.autobase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.Motors;

public class Drive {
    LinearOpMode _linearOpMode;
    Motors _motors;

    public String Parking = "Right";

    public Drive(LinearOpMode linearOpMode, Motors motors) {
        _linearOpMode = linearOpMode;
        _motors = motors;

        for (DcMotor mtr : _motors.DcMotors) {
            mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            ((DcMotorEx) mtr).setTargetPositionTolerance(150);
        }
    }

    public void SetParking() {
        if (_linearOpMode.gamepad1.right_bumper) {
            _linearOpMode.sleep(200);
            Parking = "Right";
        }
        else if (_linearOpMode.gamepad1.left_bumper) {
            _linearOpMode.sleep(200);
            Parking = "Left";
        }
    }

    public void RunToPosition(double power, int position) {
        for (DcMotor mtr : _motors.DcMotors) {
            mtr.setTargetPosition(position);
            mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        _motors.SetPower(power);
        while (isBusy() && !_linearOpMode.isStopRequested()) { }
        _motors.Stop();
    }

    public void StrafeToTicks(double power, int ticks) {
        for (int i = 0; i < _motors.DcMotors.length; i++) {
            _motors.DcMotors[i].setTargetPosition(i % 2 == 0 ? ticks : -ticks);
            _motors.DcMotors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            _motors.DcMotors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        _motors.SetPower(new double[] {
            ticks > 0 ? -power : power,
            ticks > 0 ? power : -power,
            ticks > 0 ? power : -power,
            ticks > 0 ? -power : power
        });
        while (!isAtEncoder() && !_linearOpMode.isStopRequested()) { }
        _motors.Stop();
    }

    boolean isAtEncoder() {
        if (_motors.DcMotors[0].getPower() < 0)
            return _motors.DcMotors[0].getCurrentPosition() <= _motors.DcMotors[0].getTargetPosition();
        else if (_motors.DcMotors[0].getPower() > 0)
            return _motors.DcMotors[0].getCurrentPosition() >= _motors.DcMotors[0].getTargetPosition();

        return true;
    }

    boolean isBusy() {
        for (DcMotor mtr : _motors.DcMotors) {
            if (mtr.isBusy()) return true;
        }

        return false;
    }
}
