package org.firstinspires.ftc.teamcode.playerbase;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.base.CRServos;
import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.Motors;

public class Intake {
    LinearOpMode _linearOpMode;
    CRServos _crServos;
    Motors _motors;
    final double INTAKE_POWER = 1;
    final ElapsedTime INTAKE_RUN_TIME = new ElapsedTime();
    final int INTAKE_RUN_MS = 5000;
    final int CONVEYOR_RUN_MS = 7500;
    /* slurperState: -1:running_backwards, 0:idle, 1:running_forwards, 2:post_release_runtime */
    int slurperState = 0;

    public Intake(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _crServos = new CRServos(_linearOpMode.hardwareMap, new String[] { Config.CONVEYOR_SERVO });
        _motors = new Motors(_linearOpMode.hardwareMap, new String[] { Config.INTAKE_MOTOR });
    }

    public void Execute() {
        if (_linearOpMode.gamepad1.left_bumper) {
            slurperState = -1;
            _motors.SetPower(INTAKE_POWER);
        } else if (_linearOpMode.gamepad1.right_bumper) {
            slurperState = 1;
            _motors.SetPower(-INTAKE_POWER);
            _crServos.SetPower(Config.CONVEYOR_SERVO, 1);
        } else {
            if (slurperState == 1) {
                slurperState = 2;
                INTAKE_RUN_TIME.reset();
            } else if (slurperState == -1 || INTAKE_RUN_TIME.milliseconds() > INTAKE_RUN_MS) {
                slurperState = 0;
                _motors.Stop();
            } else if (slurperState == 0 && INTAKE_RUN_TIME.milliseconds() > CONVEYOR_RUN_MS) {
                _crServos.Stop(Config.CONVEYOR_SERVO);
            }
        }
    }
}
