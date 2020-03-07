
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Drive extends CommandBase {
	
	private final DriveSubsystem m_driveSubsystem;

	public boolean finished = false;

	public Drive(DriveSubsystem subsystem){
		m_driveSubsystem = subsystem; 
		addRequirements(m_driveSubsystem);
	}

	@Override
	public void initialize(){
		System.out.println("this is getting called!!");
	}

	@Override
	public void execute() {
		m_driveSubsystem.tankDrive(1, 1); 
	}

	@Override
	public boolean isFinished(){
		return finished;
	}
}
