package org.firstinspires.ftc.teamcode.base;

import org.firstinspires.ftc.teamcode.data.ServoSetting;

public class Config {
    public static final String LOG_TAG = "5960";
    //region Motors
    public static final String FRONT_LEFT_MOTOR = "FL";
    public static final String REAR_LEFT_MOTOR = "RL";
    public static final String FRONT_RIGHT_MOTOR = "FR";
    public static final String REAR_RIGHT_MOTOR = "RR";

    public static final String[] DRIVE_MOTORS = new String[]{
            FRONT_LEFT_MOTOR,
            FRONT_RIGHT_MOTOR,
            REAR_LEFT_MOTOR,
            REAR_RIGHT_MOTOR
    };

    public static final String LIFT_MOTOR = "LIFT";

    public static final String HOOK_MOTOR = "HOOK";

    public static final String INTAKE_MOTOR = "INTAKE";

    public static final String EXTENDER_MOTOR = "EX";
    //endregion

    //region Touch_Sensors
    public static final String BASKET_SENSOR = "TS";
    public static final String LIFT_TOUCH_SENSOR = "LTS";
    public static final String[] TOUCH_SENSORS = new String[]{BASKET_SENSOR, LIFT_TOUCH_SENSOR};
    //endregion

    //region Servos
    public static final String AIRPLANE_SERVO = "AL";
    public static final String BASKET_SERVO = "AEJ";
    public static final ServoSetting[] SERVO_SETTINGS = new ServoSetting[]{
            new ServoSetting(AIRPLANE_SERVO, 1, 0.5, true), // Start, End
            new ServoSetting(BASKET_SERVO, 1, 0.7, false) // Open, Closed
    };
    //endregion

    //region CRServos
    public static final String CONVEYOR_SERVO = "Conveyor";
    public static final String PIXEL_PUSHER_SERVO = "PXLPUSHER";
    //endregion
}
