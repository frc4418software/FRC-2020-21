/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.Robot;


public class TeleopDriveCommand extends CommandBase {
  public TeleopDriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(Robot.driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.driveSubsystem.isArcadeDrive()) {
      Robot.driveSubsystem.teleopArcadeDriveWrapper(
        RobotContainer.getForwardArcadeDriveAxis(), // forward
        RobotContainer.getAngleArcadeDriveAxis()  // angle
      );
    } else {
      Robot.driveSubsystem.teleopTankDriveWrapper(
        RobotContainer.getLeftTankDriveAxis(),  // left
        RobotContainer.getRightTankDriveAxis()  // right
      );
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.driveSubsystem.stopDrive();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
	public TeleopDriveCommand() {
		// Use addRequirements() here to declare subsystem dependencies
		addRequirements(Robot.driveSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override

	public void initialize() {

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if(Robot.driveSubsystem.isArcadeDrive()) {
			Robot.driveSubsystem.teleopArcadeDriveWrapper(
				RobotContainer.getForwardArcadeDriveAxis(), // forward
				RobotContainer.getAngleArcadeDriveAxis()  // angle
			);
		} else {
			Robot.driveSubsystem.teleopTankDriveWrapper(
				RobotContainer.getLeftTankDriveAxis(),  // left
				RobotContainer.getRightTankDriveAxis()  // right
			);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.driveSubsystem.stopDrive();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
