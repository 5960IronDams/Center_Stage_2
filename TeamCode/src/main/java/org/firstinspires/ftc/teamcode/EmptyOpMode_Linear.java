package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="Empty OpMode Linear", group="Linear OpMode")
@Disabled
public class EmptyOpMode_Linear extends LinearOpMode {

    @Override
    public void runOpMode() {

        while (opModeInInit()) { }

        if (opModeIsActive()) { }
    }
}
