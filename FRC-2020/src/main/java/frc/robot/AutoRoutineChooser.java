/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

/**
 * Add your docs here.
 */
public class AutoRoutineChooser {

    //Drive Trajectories for low goal shooting and high goal shooting 
    public static Trajectory driveTrajectory;
    public static Trajectory straightErrorTrajectory;
    public static Trajectory leftPositionLowTrajectory;
    public static Trajectory centerPositionLowTrajectory;
    public static Trajectory rightPositionLowTrajectory;
    public static Trajectory leftPositionHighTrajectory;
    public static Trajectory centerPositionHighTrajectory;
    public static Trajectory rightPositionHighTrajectory;

    public static void SetDriveTrajectory() {
        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint =
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(Constants.ksVolts,
                                           Constants.kvVoltSecondsPerMeter,
                                           Constants.kaVoltSecondsSquaredPerMeter),
                Constants.kDriveKinematics,
                10);

        // Create config for trajectory
        TrajectoryConfig config =
            new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
                                 Constants.kMaxAccelerationMetersPerSecondSquared)
                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(Constants.kDriveKinematics)
                // Apply the voltage constraint
                .addConstraint(autoVoltageConstraint);
        

        //here is were we will configure all of the drive trajectories established at start
        // Example w/o PathWeaver: swerves, will establish all trajectories in similar format (DriveTrajectory will be left blank in final)
        //other formats than this and PathWeaver available... check the docs (generateTrajectory)
        driveTrajectory = TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(
                new Translation2d(1, 1),
                new Translation2d(2, -1)
            ),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0)),
            // Pass config
            config
        ); 
    }
    
    public static void DriveTrajectoryStart () {
        int autoRoutine = Robot.autoRoutineChooser.getSelected();
        int positionInput = Robot.robotPositionChooser.getSelected();
        if(autoRoutine == 0 ) {
          if(positionInput == 0) {
            if (Robot.robotPosition == 1) {
              driveTrajectory = leftPositionLowTrajectory;
            } else if (Robot.robotPosition == 2) {
              driveTrajectory = centerPositionLowTrajectory;
            } else if (Robot.robotPosition == 3) {
              driveTrajectory = rightPositionLowTrajectory;
            } else {
              driveTrajectory = straightErrorTrajectory;
            }
          }
          if (positionInput == 1) {
            driveTrajectory = leftPositionLowTrajectory;
          }
          if (positionInput ==2) {
            driveTrajectory = centerPositionLowTrajectory;
          }
          if (positionInput == 3) {
            driveTrajectory = rightPositionLowTrajectory;
          }
    
        } else if (autoRoutine == 1) {
          if(positionInput == 0) {
            if (Robot.robotPosition == 1) {
              driveTrajectory = leftPositionHighTrajectory;
            } else if (Robot.robotPosition == 2) {
              driveTrajectory = centerPositionHighTrajectory;
            } else if (Robot.robotPosition == 3) {
              driveTrajectory = rightPositionHighTrajectory;
            } else {
              driveTrajectory = straightErrorTrajectory;
            }
          }
          if (positionInput == 1) {
            driveTrajectory = leftPositionHighTrajectory;
          }
          if (positionInput ==2) {
            driveTrajectory = centerPositionHighTrajectory;
          }
          if (positionInput == 3) {
            driveTrajectory = rightPositionHighTrajectory;
          }
        }
      }

    
}
