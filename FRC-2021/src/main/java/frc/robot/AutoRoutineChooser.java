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
 * How this Routine works:
 * This routine sets all of the posible trajectories that the robot can go through in the Auto period.
 * It does this by setting different trajectories for each position/goal that is either selected by Driverstation/FMS or by Smartdashboard.
 * In the DriveTrajectory start command, depending on the information that the robot got from either DS or SD the robot will funnel the 
 * correct Trajectory into DriveTrajectory which is the base Trajectory that is referenced in the AutoCommand in robotContainer.
 */
public class AutoRoutineChooser {

    //Drive Trajectories for low goal shooting and high goal shooting, as well as an error trajectory where the robot will just drive off of the initiation line
    public static Trajectory driveTrajectory; //this will be left blank and established in the DriveTrajectoryStart method
    //set in SetDriveTrajectory Method:
    public static Trajectory straightErrorTrajectory;
    public static Trajectory leftPositionLowTrajectory;
    public static Trajectory centerPositionLowTrajectory;
    public static Trajectory rightPositionLowTrajectory;
    public static Trajectory leftPositionHighTrajectory;
    public static Trajectory centerPositionHighTrajectory;
    public static Trajectory rightPositionHighTrajectory;
    public static final double ROBOTHALFLENGTH = 0.5; // length of half/ to the rio ... actual value tbd
    public static final double SHOOTINGDISTANCELOW = .45; // distance is .45 meters from the top of the triangle on the game field
    public static final double SHOOTINGDISTANCEHIGH = 2; //distance is two meters away from the goal

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

        double PositionL = (.9466-(Math.abs(Robot.distance- ROBOTHALFLENGTH)));

        leftPositionLowTrajectory = TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through first one, is in front of shooting zone, passes through second one, is in position to shoot to low goal
            List.of(
                new Translation2d(PositionL, .4399),
                new Translation2d(PositionL, (SHOOTINGDISTANCELOW+.4394))
            ),
            // final position 90 degrees from origional position and calculated distance from start
            new Pose2d(PositionL, (SHOOTINGDISTANCELOW+.4394), new Rotation2d((Math.PI/2))),
            // Pass config
            config
        );
        
        double PositionR = (2.1918-(Math.abs(Robot.distance- ROBOTHALFLENGTH)));
        
        //this one just turns the opposite way
        rightPositionLowTrajectory = TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through first one, is in front of shooting zone, passes through second one, is in position to shoot to low goal
            List.of(
                new Translation2d(PositionR, -.4399),
                new Translation2d(PositionR, -(SHOOTINGDISTANCELOW+.4394))
            ),
            // final position 90 degrees from origional position and calculated distance from start
            new Pose2d(PositionR, (SHOOTINGDISTANCELOW+.4394), new Rotation2d(-Math.PI/2)),
            // Pass config
            config
        );

        //assumed to be right in front of low goal, just drives forward into shooting distance
        centerPositionLowTrajectory = TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction (facing goal)
            new Pose2d(0, 0, new Rotation2d(0)),
            // moves into shooting distance
            List.of(
                new Translation2d((SHOOTINGDISTANCELOW+.4394), 0)
            ),
            // final position in shooting position
            new Pose2d((SHOOTINGDISTANCELOW+.4394), 0, new Rotation2d(0)),
            // Pass config
            config
        );
    }
    
    //this command is run at the start of the AutoCommand, it sets the DriveTrajectory to one of the 6 posible trajectories based on the goal and the location of the robot
    /*Current possible configurations: (default decided by DS/FMS, can be changed in SmartDashboard; low goal is also the default and can be changed in SD)
    - Low goal shooting: left station, right station, center station
    - High goal shooting: left station, right station, center station (tbd whether or not this will be functional)
    */
    public static void DriveTrajectoryStart () {
        int autoRoutine = Robot.autoRoutineChooser.getSelected();
        int positionInput = Robot.robotPositionChooser.getSelected();

        //if the auto routine is set to low goal shooting (this is the default command)
        if(autoRoutine == 0 ) {
          // if the position is to be given by Driverstation / FMS (this is the default command) 1= left station, 2= center, 3=right
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
          //This is if the position is manually given/ set in SmartDashboard, numbers for stations are the same as if it were given by DS
          if (positionInput == 1) {
            driveTrajectory = leftPositionLowTrajectory;
          }
          if (positionInput ==2) {
            driveTrajectory = centerPositionLowTrajectory;
          }
          if (positionInput == 3) {
            driveTrajectory = rightPositionLowTrajectory;
          }
    
        } 
        //This is if we are aiming for the high goal... everything is the same as low goal but the trajectories are the high ones
        else if (autoRoutine == 1) {
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
