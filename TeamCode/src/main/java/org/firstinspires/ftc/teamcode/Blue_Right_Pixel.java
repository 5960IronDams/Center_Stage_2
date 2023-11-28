package org.firstinspires.ftc.teamcode;

import android.provider.MediaStore;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autobase.AutoConfig;
import org.firstinspires.ftc.teamcode.autobase.ConfigRight;
import org.firstinspires.ftc.teamcode.autobase.Drive;
import org.firstinspires.ftc.teamcode.autobase.Turn;
import org.firstinspires.ftc.teamcode.base.CRServos;
import org.firstinspires.ftc.teamcode.base.Config;
import org.firstinspires.ftc.teamcode.base.Distances;
import org.firstinspires.ftc.teamcode.base.Motors;


@Autonomous(name="Blue Right Pixel", group="Auto")
@Disabled
public class Blue_Right_Pixel extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.setAutoClear(true);

        Motors driveMotors = new Motors(hardwareMap, Config.DRIVE_MOTORS);
        Distances distances = new Distances(hardwareMap, new String[] { Config.DS_RIGHT_RIGHT, Config.DS_RIGHT_LEFT });
        Drive drive = new Drive(this, driveMotors);
        Turn turn = new Turn(this, driveMotors);
        CRServos crServos = new CRServos(hardwareMap, new String[] { Config.PIXEL_PUSHER_SERVO });

        while (opModeInInit()) {
            drive.SetParking();
            telemetry.addData("Parking", drive.Parking);
            telemetry.update();
        }

        if (opModeIsActive()) {
            /* Drive to read prop with distance sensor */
            drive.RunToPosition(AutoConfig.MAX_MOTOR_SPEED, AutoConfig.DRIVE_TO_READ_SPIKE_MARK);
            sleep(AutoConfig.MOTOR_PAUSE_MS);

            /* Read the prop to detect spike mark placement. Store it for later use. */
            String selectedSpikeMark = ConfigRight.SetSpikeMark(distances.GetActiveSensor());

            /* Set the movement based on what spike mark we detected */
            int spikeMarkXTicks = selectedSpikeMark.equalsIgnoreCase("Left") ?
                    ConfigRight.LEFT_SPIKE_MARK_X : selectedSpikeMark.equalsIgnoreCase("Middle") ?
                    ConfigRight.MIDDLE_SPIKE_MARK_X : ConfigRight.RIGHT_SPIKE_MARK_X;

            int spikeMarkYTicks = selectedSpikeMark.equalsIgnoreCase("Left") ?
                    ConfigRight.LEFT_SPIKE_MARK_Y : selectedSpikeMark.equalsIgnoreCase("Middle") ?
                    ConfigRight.MIDDLE_SPIKE_MARK_Y : ConfigRight.RIGHT_SPIKE_MARK_Y;

            int spikeMarkYTicksToWall;

            if (spikeMarkXTicks != 0 && !selectedSpikeMark.equalsIgnoreCase("Left")) {
                /* If we don't need to place on the left spike mark then we can ignore the
                 *  extra moved to get under the bar. */
                drive.StrafeToTicks(AutoConfig.MAX_MOTOR_SPEED, spikeMarkXTicks);
            } else if (selectedSpikeMark.equalsIgnoreCase("Left")) {
                /* We need to turn and approach the spike mark a little different when we
                 *  are under the bar. */
                turn.ToDegrees(AutoConfig.MAX_MOTOR_SPEED, ConfigRight.LEFT_SPIKE_MARK_TURN);
                sleep(AutoConfig.MOTOR_PAUSE_MS);
                drive.RunToPosition(AutoConfig.MAX_MOTOR_SPEED, ConfigRight.LEFT_UNDER_BAR_Y);
            }

            /* Drop the pixel on the spike mark */
            crServos.SetPower(Config.PIXEL_PUSHER_SERVO, 1);
            sleep(AutoConfig.PIXEL_DROP_DELAY_MS);

            if (selectedSpikeMark.equalsIgnoreCase("Left")) {
                /* We need to back away and straighten back out when we
                 *  are under the bar. */
                drive.RunToPosition(AutoConfig.MAX_MOTOR_SPEED, -ConfigRight.LEFT_UNDER_BAR_Y);
                sleep(AutoConfig.MOTOR_PAUSE_MS);
                turn.ToDegrees(AutoConfig.MAX_MOTOR_SPEED, ConfigRight.LEFT_RESET_TURN);
                sleep(AutoConfig.MOTOR_PAUSE_MS);
            }

            if (!selectedSpikeMark.equalsIgnoreCase("Right")) {
                /* If we are not on the furthest spike mark strafe over to it. */
                spikeMarkYTicksToWall = spikeMarkYTicks - AutoConfig.BACKAWAY_PIXEL_TICKS;
                drive.RunToPosition(AutoConfig.MAX_MOTOR_SPEED, -spikeMarkYTicksToWall);
                sleep(AutoConfig.MOTOR_PAUSE_MS);
                drive.StrafeToTicks(AutoConfig.MAX_MOTOR_SPEED, -spikeMarkXTicks - ConfigRight.STRAFE_TO_RIGHT_SPIKE_MARK_OFFSET);
                sleep(AutoConfig.MOTOR_PAUSE_MS);
            }

            /* Backup to the wall and stop the pixel ejector servo */
            spikeMarkYTicksToWall = spikeMarkYTicks - (spikeMarkYTicks - AutoConfig.BACKAWAY_PIXEL_TICKS) - AutoConfig.SPIKE_MARK_TO_WALL_OFFSET;
            drive.RunToPosition(AutoConfig.MAX_MOTOR_SPEED, -spikeMarkYTicksToWall);
            crServos.Stop(Config.PIXEL_PUSHER_SERVO);
            sleep(AutoConfig.MOTOR_PAUSE_MS);

            /* Turn to face the backboard */
            turn.ToDegrees(AutoConfig.MAX_MOTOR_SPEED, ConfigRight.WALL_TURN_TO_BACKDROP);
            sleep(AutoConfig.MOTOR_PAUSE_MS);

            /* Drive under the bars - Only needed on Red Left & Blue Right */
            drive.RunToPosition(AutoConfig.MAX_MOTOR_SPEED, AutoConfig.DRIVE_UNDER_BAR_TICKS);
        }
    }
}
