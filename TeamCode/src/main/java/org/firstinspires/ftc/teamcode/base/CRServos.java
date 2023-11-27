package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Dictionary;
import java.util.Hashtable;

public class CRServos {
    Dictionary<String, CRServo> _servos;

    public CRServos(HardwareMap hardwareMap, String[] dsServos) {
        _servos = new Hashtable<>();
        for (String servo : dsServos) {
            _servos.put(servo, hardwareMap.get(CRServo.class, servo));
        }
    }

    public void Stop(String key) {
        SetPower(key, 0);
    }

    public void SetPower(String key, double power) {
        _servos.get(key).setPower(power);
    }
}
