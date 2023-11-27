package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Dictionary;
import java.util.Hashtable;

public class Motors {
    public DcMotor[] DcMotors;
    public Dictionary<String, DcMotor> Motors = new Hashtable<>();

    public Motors(HardwareMap hardwareMap, String[] dsMotors) {
        DcMotors = new DcMotor[dsMotors.length];
        for (int i = 0; i < dsMotors.length; i++) {
            DcMotors[i] = hardwareMap.get(DcMotor.class, dsMotors[i]);
            Motors.put(dsMotors[i], DcMotors[i]);

            DcMotors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            DcMotors[i].setDirection((i % 2 == 0 ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD));
        }
    }

    public void Stop() {
        SetPower(0);
    }

    public void SetPower(double power) {
        for (DcMotor mtr: DcMotors) {
            mtr.setPower(power);
        }
    }

    public void SetPower(double[] powers) {
        for (int i = 0; i < powers.length; i++) {
            DcMotors[i].setPower(powers[i]);
        }
    }

    public int GetCurrentPosition(String key) {
        return Motors.get(key).getCurrentPosition();
    }
}
