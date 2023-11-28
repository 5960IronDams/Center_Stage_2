package org.firstinspires.ftc.teamcode.autobase;

import org.firstinspires.ftc.teamcode.base.Config;

public class ConfigRight {
    public static final int LEFT_SPIKE_MARK_Y = 1550;
    public static final int LEFT_SPIKE_MARK_X = 300;
    public static final double LEFT_SPIKE_MARK_TURN = 87;
    public static final int LEFT_UNDER_BAR_Y = 300;

    public static final double LEFT_RESET_TURN = 2;

    public static final int MIDDLE_SPIKE_MARK_Y = 1770;
    public static final int MIDDLE_SPIKE_MARK_X = 0;

    public static final int STRAFE_TO_RIGHT_SPIKE_MARK_OFFSET = 300;

    public static final int RIGHT_SPIKE_MARK_Y = 1250;
    public static final int RIGHT_SPIKE_MARK_X = -250;

    public static final double WALL_TURN_TO_BACKDROP = 84;

    public static String SelectedSpikeMark = "";
    public static String SetSpikeMark(String activeDistance) {
        if (activeDistance.equalsIgnoreCase(Config.DS_RIGHT_RIGHT)) SelectedSpikeMark = "Right";
        else if (activeDistance.equalsIgnoreCase(Config.DS_RIGHT_LEFT)) SelectedSpikeMark = "Middle";
        else SelectedSpikeMark = "Left";

        return SelectedSpikeMark;
    }
}
