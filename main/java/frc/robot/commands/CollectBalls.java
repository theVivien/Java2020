
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class CollectBalls extends CommandBase {

	public final ShooterSubsystem m_shooterSubsystem;

	public CollectBalls(ShooterSubsystem subsystem) {
		m_shooterSubsystem = subsystem;
		addRequirements(m_shooterSubsystem);
	}

	@Override
	public void initialize() {
		m_shooterSubsystem.conveyorMotorForward();
		m_shooterSubsystem.feederOff();
		m_shooterSubsystem.shooterOn();
	}

	@Override
	public boolean isFinished() {
	  return true;
	}
	
}