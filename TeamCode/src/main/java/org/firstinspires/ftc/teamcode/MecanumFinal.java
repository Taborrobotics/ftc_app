
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp

public class MecanumFinal extends LinearOpMode {

    private DcMotor m1, m2,m3,m4;

    private double rightPower = 0;
    private double leftPower = 0;

    private boolean opened = false;



    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        m1 = hardwareMap.get(DcMotor.class, "Motor1");
        m2 = hardwareMap.get(DcMotor.class, "Motor2");
        m3 = hardwareMap.get(DcMotor.class,"Motor3");
        m4 = hardwareMap.get(DcMotor.class,"Motor4");


        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            rightPower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x,-0.5,0.5);
            leftPower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x,-0.5,0.5);



            double turn = Range.clip(gamepad1.left_stick_y,-0.5,0.5);
            double slide = Range.clip(gamepad1.right_stick_x, -0.5, 0.5);

            if (turn > 0){
                m1.setPower(turn);
                m2.setPower(-turn);
                m3.setPower(turn);
                m4.setPower(-turn);
            }
            else if(turn < 0){
                m1.setPower(-turn);
                m2.setPower(turn);
                m3.setPower(-turn);
                m4.setPower(turn);
            }
            if (slide > 0){
                m1.setPower(-rightPower);
                m2.setPower(-rightPower);
                m3.setPower(leftPower);
                m4.setPower(leftPower);
            }
            else if (slide < 0){
                m1.setPower(rightPower);
                m2.setPower(rightPower);
                m3.setPower(-leftPower);
                m4.setPower(-leftPower);
            }
        }
    }

}
