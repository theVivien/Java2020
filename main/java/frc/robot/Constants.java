
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button; 

public final class Constants {
    public static final int kxConPort = 0; 
    public static final double kFastSpeed = 1; 
    public static final double kDefaultSpeed = .5;

    // Motors 
    public static final int kDriveX = 4;
    public static final int kDriveY = 3; 
    public static final int kFeederMotor = 0;
    public static final int kShooterMotor = 0; 
    public static final int kConveyorMotor = 0;  
    public static final int kElevatorMotor = 0; 
    public static final int kServo = 0; 

    //btns 
    public static final XboxController.Button kCollectBalls = Button.kY;
    public static final XboxController.Button kElevatorGoUp = Button.kBumperRight;
    public static final XboxController.Button kElevatorGoDown = Button.kBumperLeft;

}
