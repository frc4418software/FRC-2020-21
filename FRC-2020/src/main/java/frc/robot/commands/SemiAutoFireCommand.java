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

public class SemiAutoFireCommand extends CommandBase {
  /**
   * Creates a new SemiAutoFireCommand.
   */
  public SemiAutoFireCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Bottom Fire", Robot.manipulatorsubsystem.getBottomFireMotor());
    SmartDashboard.putNumber("Top Fire", Robot.manipulatorsubsystem.getTopFireMotor());
    SmartDashboard.putNumber("Load", Robot.manipulatorsubsystem.getLoadMotor());
    Robot.manipulatorsubsystem.setBottomFireMotor(.24);
    Robot.manipulatorsubsystem.setTopFireMotor(-.6);
    try{
      Thread.sleep(1000);
    } catch (InterruptedException e){
      e.printStackTrace();
    }
    Robot.manipulatorsubsystem.setLoadMotor(.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.manipulatorsubsystem.stopBottomFireMotor();
    Robot.manipulatorsubsystem.stopTopFireMotor();
    Robot.manipulatorsubsystem.stopLoadMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
