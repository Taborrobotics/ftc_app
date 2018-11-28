package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.CENTER;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;

import org.firstinspires.ftc.teamcode.WestCoastRobot;

//blue alliance,drive forward

@Autonomous
//@Disabled
public class WestAutonomous1 extends OpMode
{


    private WestCoastRobot robot = new WestCoastRobot();

    private int leftPos = 200;
    private int centerPos = 400;
    private int rightPos = 600;

    private int rotatePos = 300;

    private int target = 100;

    private int ret;

    public static final String TAG = "Vuforia VuMark Sample";



    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;

    VuforiaTrackable relicTemplate;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        robot.make(hardwareMap,"Motor1","Motor2","Motor3","Motor4","Lift_Motor","Grabber_Motor","Grab1","Grab2","Drop1","Drop2","Place");
        robot.setUpEncoders();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "ASHHQIj/////AAAAGXYJVW7cAUFsgkCKi6Nhif9NSD8eT9qEc1ACRGnc4hTvEXGEXuyMiud30yKeNLWeVSRaauEwn7EsvmW3hLBIPAoP1O5tVZ3AKXoP7gtDsaqTX9zP457gxk4vDSBmO4vNsHgl4sa+ijZTb50UJ3nvA92U/4lPicHcyLYenOpWGXEe/4MJF1P6uQ/Hp4M0n2mrLRw8Q5xzjyV5CSY+Vv1H4/mCYH0zlwOl/ScN/ibmouIT6mOJfWQjlvx8xaIpbg5slN5j7L7CGrRs2284z1WF6aSn7Fo20IUe/FevV+ZLKmgAMZGmizpx91L7SskSWNSyjn6S/a/wDVPCScUm/iEEouKOEAa4yMkCYll62tn6VR8q";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

        relicTrackables.activate();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop(){

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        robot.initServo();
        boolean done = false;

        while (!done){
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN){

                if (vuMark == RIGHT){
                    robot.goToPosition(rightPos,rightPos,0.5);
                    ret = -rightPos;
                }
                else if(vuMark == CENTER){
                    robot.goToPosition(centerPos,centerPos,0.5);
                    ret = -centerPos;
                }
                else if (vuMark == LEFT){
                    robot.goToPosition(leftPos,leftPos,0.5);
                    ret = -leftPos;
                }
                done = true;
            }
        }

        robot.rotate(false,rotatePos,0.2);

        robot.goToPosition(target,target,0.2);

        robot.dropBlock(true);

        robot.goToPosition(-target,-target,0.2);

        robot.rotate(false,rotatePos,0.2);

        robot.goToPosition(ret,ret,0.5);


    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }


}