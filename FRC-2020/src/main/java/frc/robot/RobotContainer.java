/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.ToggleArcadeDriveCommand;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  // private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // private final ExampleCommand m_autoCommand = new
  // ExampleCommand(m_exampleSubsystem);
  private DriveSubsystem drive = new DriveSubsystem();

  // Create joysticks
  private static final Joystick X3D_LEFT = new Joystick(Constants.X3D_LEFT_JOYSTICK_ID),
      X3D_RIGHT = new Joystick(Constants.X3D_RIGHT_JOYSTICK_ID), GAMEPAD = new Joystick(Constants.GAMEPAD_JOYSTICK_ID);

  // Get axis for specific functions
  public static double getLeftTankDriveAxis() {
    return X3D_LEFT.getRawAxis(Constants.LEFT_TANK_DRIVE_AXIS_ID);
  }

  public static double getRightTankDriveAxis() {
    return X3D_RIGHT.getRawAxis(Constants.RIGHT_TANK_DRIVE_AXIS_ID);
  }

  public static double getForwardArcadeDriveAxis() {
    return X3D_RIGHT.getRawAxis(Constants.FORWARD_ARCADE_DRIVE_AXIS_ID);
  }

  public static double getAngleArcadeDriveAxis() {
    return X3D_RIGHT.getRawAxis(Constants.ANGLE_ARCADE_DRIVE_AXIS_ID);
  }

  public static double getClimberAxis() {
    return GAMEPAD.getRawAxis(Constants.CLIMB_AXIS_ID);
  }

  // Create and assign default buttons
  public static JoystickButton toggleArcadeDriveButton = new JoystickButton(X3D_RIGHT,
      Constants.TOGGLE_ARCADE_DRIVE_BUTOON_ID);
  public static JoystickButton driveStraightButton = new JoystickButton(X3D_RIGHT, Constants.DRIVE_STRAIGHT_BUTTON_ID);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    toggleArcadeDriveButton.whenPressed(new ToggleArcadeDriveCommand());
    driveStraightButton.whileHeld(new DriveStraightCommand());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

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
    
            
    // An example trajectory to follow.  All units in meters.
    Trajectory driveTrajectory;
    if (Robot.robotPosition == 1) {
    
    } else if (Robot.robotPosition == 2) {

    } else if (Robot.robotPosition == 3) {

    } else {

    }
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

    

    RamseteCommand ramseteCommand = new RamseteCommand(
      driveTrajectory, 
      drive::getPose, 
      new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta), 
      new SimpleMotorFeedforward(Constants.ksVolts, Constants.kvVoltSecondsPerMeter, Constants.kaVoltSecondsSquaredPerMeter), 
      Constants.kDriveKinematics, 
      drive::getWheelSpeeds, 
      new PIDController(Constants.kPDriveVel, 0, 0), 
      new PIDController(Constants.kPDriveVel, 0, 0),
      drive::driveVolts, 
      Robot.driveSubsystem);
    

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> Robot.driveSubsystem.driveVolts(0, 0));
  }
}
