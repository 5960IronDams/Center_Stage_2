package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.Dictionary;
import java.util.Hashtable;

public class LimitSwitches {
    Dictionary<String, TouchSensor> _sensors;

    public LimitSwitches(HardwareMap hardwareMap) {
        _sensors = new Hashtable<>();
        for (int i = 0; i < Config.TOUCH_SENSORS.length; i++) {
            _sensors.put(Config.TOUCH_SENSORS[i], hardwareMap.get(TouchSensor.class, Config.TOUCH_SENSORS[i]));
        }
    }

    public TouchSensor GetSensor(String key) {
        return _sensors.get(key);
    }

    public boolean IsPressed(String key) {
        return _sensors.get(key).isPressed();
    }
}
