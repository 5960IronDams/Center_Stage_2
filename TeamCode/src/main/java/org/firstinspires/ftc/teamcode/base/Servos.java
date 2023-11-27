package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.data.ServoSetting;

import java.util.Dictionary;
import java.util.Hashtable;

public class Servos {
    Dictionary<String, ServoSetting> _servos;

    public Servos(HardwareMap hardwareMap) {
        _servos = new Hashtable<>();

        for (ServoSetting setting: Config.SERVO_SETTINGS) {
            setting.Servo = hardwareMap.get(Servo.class, setting.NAME);
            _servos.put(setting.NAME, setting);

            if (setting.START_IN_UPPER_POS) {
                MoveToUpperLimit(setting.NAME);
            } else {
                MoveToLowerLimit(setting.NAME);
            }
        }
    }

    public void MoveToUpperLimit(String key) {
        ServoSetting setting = _servos.get(key);
        if (!setting.IsInUpperPosition) {
            setting.Servo.setPosition(setting.UPPER_LIMIT);
            setting.IsInUpperPosition = true;
        }
    }

    public void MoveToLowerLimit(String key) {
        ServoSetting setting = _servos.get(key);
        if (setting.IsInUpperPosition) {
            setting.Servo.setPosition(setting.LOWER_LIMIT);
            setting.IsInUpperPosition = false;
        }
    }
}
