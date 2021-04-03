// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class SwitchDirectionCommand extends CommandBase {
  private boolean direction;
  private boolean amOverridingDirection;
  /** Creates a new SwitchDirectionCommand. */
  public SwitchDirectionCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    amOverridingDirection = false;
  }

  // allow for the direction to be manually set
  public SwitchDirectionCommand(boolean directionForward) {
    direction = directionForward;
    amOverridingDirection = directionForward;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // set the direction that is forward, true is forward
    if(amOverridingDirection) {
      Constants.setRobotDirection(direction);
    } else {
      Constants.toggleRobotDirection();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
