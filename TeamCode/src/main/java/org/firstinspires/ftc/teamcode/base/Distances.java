package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Distances {
    Dictionary<String, DistanceSensor> _sensors;

    public Distances(HardwareMap hardwareMap, String[] dsSensors) {
        _sensors = new Hashtable<>();
        for (String key: dsSensors) {
            _sensors.put(key, hardwareMap.get(DistanceSensor.class, key));
        }
    }

    public double GetDistance(String key, DistanceUnit distanceUnit) {
        return _sensors.get(key).getDistance(distanceUnit);
    }

    public String GetActiveSensor() {
        String activeSensor = "";
        double minDistance = 1000;

        Enumeration<String> en = _sensors.keys();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            double dist = _sensors.get(key).getDistance(DistanceUnit.INCH);
            if (dist < 50 && dist < minDistance) {
                minDistance = dist;
                activeSensor = key;
            }
        }

        return activeSensor;
    }
}
