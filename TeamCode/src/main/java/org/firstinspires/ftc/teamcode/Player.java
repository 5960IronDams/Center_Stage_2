package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.LimitSwitches;
import org.firstinspires.ftc.teamcode.base.Servos;
import org.firstinspires.ftc.teamcode.playerbase.AirPlane;
import org.firstinspires.ftc.teamcode.playerbase.Drive;
import org.firstinspires.ftc.teamcode.playerbase.Extender;
import org.firstinspires.ftc.teamcode.playerbase.Hook;
import org.firstinspires.ftc.teamcode.playerbase.Intake;
import org.firstinspires.ftc.teamcode.playerbase.Lift;


@TeleOp(name="Driver", group="Player")
@Disabled
public class Player extends LinearOpMode {
    @Override
    public void runOpMode() {
        Servos _servos = new Servos(hardwareMap);
        LimitSwitches _limitSwitches = new LimitSwitches(hardwareMap);

        Drive _driver = new Drive(this, _limitSwitches);
        Lift _lift = new Lift(this, _limitSwitches);
        Extender _extender = new Extender(this, _limitSwitches, _servos);
        AirPlane _airPlane = new AirPlane(this, _servos);
        Hook _hook = new Hook(this);
        Intake _intake = new Intake(this);

        while (opModeInInit()) { }

        if (opModeIsActive()) {
            _driver.RunMecanum();
            _lift.Execute();
            _extender.Execute();
            _airPlane.Execute();
            _hook.Execute();
            _intake.Execute();
        }
    }
}
