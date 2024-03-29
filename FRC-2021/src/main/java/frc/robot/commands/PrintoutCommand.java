/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;


public class PrintoutCommand extends CommandBase {
  public PrintoutCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Left Motor", Robot.driveSubsystem.getLeftPercent());
    SmartDashboard.putNumber("Right Motor", Robot.driveSubsystem.getRightPercent());

    SmartDashboard.putNumber("Left Distance", Robot.driveSubsystem.getLeftDistance());
    SmartDashboard.putNumber("Right Distance", Robot.driveSubsystem.getRightDistance());

    SmartDashboard.putNumber("Left RPM", Robot.driveSubsystem.getLeftDistance());
    SmartDashboard.putNumber("Right RPM", Robot.driveSubsystem.getRightDistance());

    // SmartDashboard.putNumber("Gyro", Robot.sensorsSubsystem.getGyroValue());

    // SmartDashboard.putNumber("Accel X", Robot.sensorsSubsystem.getDriveAccelX());
    // SmartDashboard.putNumber("Accel Y", Robot.sensorsSubsystem.getDriveAccelY());
    // SmartDashboard.putNumber("Accel Z", Robot.sensorsSubsystem.getDriveAccelZ());  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
