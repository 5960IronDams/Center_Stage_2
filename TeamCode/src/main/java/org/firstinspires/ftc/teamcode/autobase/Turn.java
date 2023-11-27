package org.firstinspires.ftc.teamcode.autobase;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.Motors;

public class Turn {
    LinearOpMode _linearOpMode;
    Motors _motors;

    BNO055IMU.Parameters _parameters;
    BNO055IMU _imu;
    Orientation _angles;

    public Turn(LinearOpMode linearOpMode, Motors motors) {
        _linearOpMode = linearOpMode;
        _motors = motors;

        _parameters = new BNO055IMU.Parameters();
        _parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        _parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        _parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample OpMode
        _parameters.loggingEnabled = true;
        _parameters.loggingTag = Config.LOG_TAG;
        _parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        _imu = linearOpMode.hardwareMap.get(BNO055IMU.class, "imu");
        _imu.initialize(_parameters);
    }

    public void ToDegrees(double power, double degrees) {
        for (DcMotor mtr: _motors.DcMotors) {
            mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        _angles = _imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentDegrees = AngleUnit.DEGREES.fromUnit(_angles.angleUnit, _angles.firstAngle);

        Log.i(Config.LOG_TAG, "===== STARTING TURN TO DEGREE =====");
        Log.i(Config.LOG_TAG, "Target Degrees:" + degrees);
        Log.i(Config.LOG_TAG, "Current Degrees:" + currentDegrees);

        if (degrees < currentDegrees) {
            _motors.SetPower(new double[] { -power, power, -power, power });
            while (degrees < currentDegrees) {
                _angles = _imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                currentDegrees = AngleUnit.DEGREES.fromUnit(_angles.angleUnit, _angles.firstAngle);
            }
            _motors.Stop();
        } else if (degrees > currentDegrees) {
            _motors.SetPower(new double[] { power, -power, power, -power });
            while (degrees > currentDegrees) {
                _angles = _imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                currentDegrees = AngleUnit.DEGREES.fromUnit(_angles.angleUnit, _angles.firstAngle);
            }
            _motors.Stop();
        }

        Log.i(Config.LOG_TAG, "===== ENDING TURN TO DEGREE =====");
        Log.i(Config.LOG_TAG, "Target Degrees:" + degrees);
        Log.i(Config.LOG_TAG, "Current Degrees:" + currentDegrees);
    }
}
