
package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

import org.opencv.core.Core;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
	public Camera() {
		LidarThing lidar = new LidarThing(); 

		Thread m_visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			final UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			final CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			final CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			final Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.

			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat. If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image

				double distance = lidar.getDistance();
				Scalar color;

				if (distance <= 100 && distance >= 20) {
					color = new Scalar(0, 179, 89);
					Imgproc.putText(mat, "EXECUTE ORDER 66", new Point(0, 240), Core.FONT_HERSHEY_SIMPLEX, 2,
							new Scalar(0, 255, 255), 3);
				} else {
					color = new Scalar(51, 0, 255);
				}

				Imgproc.putText(mat, Double.toString(Math.round(distance * 100.0) / 100.0), new Point(0, 450),
						Core.FONT_HERSHEY_SIMPLEX, 1.5, color, 3);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		m_visionThread.setDaemon(true);

		m_visionThread.start();
	}

	@Override
	public void periodic() {

	}
}
