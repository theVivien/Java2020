
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LidarThing extends SubsystemBase {
	private Counter lidar;
	private static final double offset = -4.0;
	private static final double factor = 39400;

	public LidarThing() {
		lidar = new Counter(0);
		lidar.setMaxPeriod(1.0);
		lidar.setSemiPeriodMode(true);
		lidar.reset();
	}

	public double getDistance() {
		if (lidar.get() < 1) {
			return 0.0;
		}
		return (lidar.getPeriod() * factor + offset);
	}

	@Override
	public void periodic() {
		double distance = getDistance(); 
		SmartDashboard.putNumber("Lidar_Distance", distance); 
	}
}
