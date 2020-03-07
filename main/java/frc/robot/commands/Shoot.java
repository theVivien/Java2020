
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends CommandBase {

	private final ShooterSubsystem m_shooterSubsystem; 

	public Shoot(ShooterSubsystem subsystem) {
		m_shooterSubsystem = subsystem;
	}

	@Override
	public void initialize() { 
		m_shooterSubsystem.feederOff();
		m_shooterSubsystem.shooterOn();
	}
	
}