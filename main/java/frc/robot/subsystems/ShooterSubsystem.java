
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
	WPI_TalonSRX feederMotor;
	WPI_TalonSRX shooterMotor; 
	WPI_TalonSRX conveyorMotor; 
	public ShooterSubsystem() {
		feederMotor = new WPI_TalonSRX(Constants.kFeederMotor);
		shooterMotor = new WPI_TalonSRX(Constants.kShooterMotor); 
		conveyorMotor = new WPI_TalonSRX(Constants.kConveyorMotor);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void feederOn(){
		feederMotor.set(1);
	}
	public void feederOff(){
		feederMotor.set(0);
	}

	public void shooterOn(){
		shooterMotor.set(1);
	}
	public void shooterOff(){
		shooterMotor.set(0);
	}

	public void conveyorMotorForward(){
		conveyorMotor.set(1);
	}
	public void conveyorMotorBackward(){
		conveyorMotor.set(-1);
	}
	public void conveyorMotorOff(){
		conveyorMotor.set(0); 
	}
}
