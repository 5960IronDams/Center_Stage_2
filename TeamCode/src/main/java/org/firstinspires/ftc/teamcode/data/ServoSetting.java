package org.firstinspires.ftc.teamcode.data;

import com.qualcomm.robotcore.hardware.Servo;


public class ServoSetting {
    public final String NAME;

    public final double UPPER_LIMIT;

    public final double LOWER_LIMIT;

    public final boolean START_IN_UPPER_POS;

    public boolean IsInUpperPosition;

    public Servo Servo;

    public ServoSetting(String name, double upperLimit, double lowerLimit, boolean startInUpperPos) {
        NAME = name;
        UPPER_LIMIT = upperLimit;
        LOWER_LIMIT = lowerLimit;
        START_IN_UPPER_POS = startInUpperPos;
    }
}
