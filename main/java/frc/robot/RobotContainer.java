/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.CollectBalls;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LidarThing;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import javax.annotation.meta.When;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
	private final DriveSubsystem driveThing = new DriveSubsystem();
	private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
	private final Drive m_autoCommand = new Drive(driveThing);
	private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();  
	private final LidarThing m_lidarThing; 
	AHRS ahrs;
	private NavX nx;
	// private final CollectBalls m_collectBalls = new CollectBalls(); 
	// private final Camera camera = new Camera();
	XboxController xCon = new XboxController(Constants.kxConPort); 
	
	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();

		driveThing.setDefaultCommand(
			new RunCommand(() -> driveThing
				.tankDrive(xCon.getY(GenericHID.Hand.kLeft), 
									xCon.getY(GenericHID.Hand.kRight)), 
									driveThing));
		driveThing.speedControl(Constants.kDefaultSpeed);
		m_lidarThing = new LidarThing();
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
			nx = new NavX(ahrs);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}

	/**
	 * Use this method to define your button->command mappings.  Buttons can be created by
	 * instantiating a {@link GenericHID} or one of its subclasses ({@link
	 * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
	 * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		new JoystickButton(xCon, Button.kBumperLeft.value)
			.whenPressed(() -> driveThing.speedControl(Constants.kFastSpeed))
			.whenReleased(() -> driveThing.speedControl(Constants.kDefaultSpeed));

		new JoystickButton(xCon, Constants.kCollectBalls.value)
			.whileHeld(new CollectBalls(m_shooterSubsystem)); 
			
		new JoystickButton(xCon, Constants.kElevatorGoUp.value)
			.whileHeld(() -> m_elevatorSubsystem.actuatorOpen());

		new JoystickButton(xCon, Constants.kElevatorGoDown.value)
			.whileHeld(() -> m_elevatorSubsystem.actuatorClose()); 
	}


	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return m_autoCommand;
	}
}
