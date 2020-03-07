/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

	WPI_TalonSRX motor = new WPI_TalonSRX(Constants.kElevatorMotor); 
	Servo actuator = new Servo(Constants.kServo); 

	public ElevatorSubsystem() {
		actuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
	}

	public void goUp(){
		motor.set(1);  
	}

	public void goDown(){
		motor.set(-1);
	}

	public void actuatorOpen(){
		actuator.setSpeed(1.0);
	}

	public void actuatorClose(){
		actuator.setSpeed(-1.0); 
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
