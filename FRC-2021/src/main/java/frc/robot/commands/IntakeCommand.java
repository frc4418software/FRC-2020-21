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


public class IntakeCommand extends CommandBase {
  public IntakeCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Top Intake Motor", Robot.manipulatorsubsystem.getTopIntakeMotor());
    SmartDashboard.putNumber("Bottom Intake Motor", Robot.manipulatorsubsystem.getBottomIntakeMotor());
    
    Robot.manipulatorsubsystem.setTopIntakeMotor(-1.0);
    Robot.manipulatorsubsystem.setBottomIntakeMotor(-1.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.manipulatorsubsystem.setTopIntakeMotor(0.0);
    Robot.manipulatorsubsystem.setBottomIntakeMotor(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
