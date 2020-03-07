
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveSubsystem extends SubsystemBase {

	DifferentialDrive diffDrive = new DifferentialDrive(new WPI_TalonSRX(Constants.kDriveX), 
														new WPI_TalonSRX(Constants.kDriveY));

	public DriveSubsystem() {
	}                                                                                                                                                                                          

	@Override
	public void periodic() {
	}

	public void tankDrive(double left, double right) {
		diffDrive.tankDrive(left / -1, right / -1);
	}

	public void speedControl(double maxSpeed) {
		diffDrive.setMaxOutput(maxSpeed);
	}
}
