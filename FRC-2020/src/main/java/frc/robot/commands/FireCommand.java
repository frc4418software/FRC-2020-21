/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class FireCommand extends CommandBase {
  /**
   * Creates a new FireCommand.
   */
  public FireCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Top Fire", Robot.manipulatorsubsystem.getTopFireMotor());
    SmartDashboard.putNumber("Bottom Fire", Robot.manipulatorsubsystem.getBottomFireMotor());
    /*if(Robot.manipulatorsubsystem.launchSpin == false) {
      for(int i=0; i <= 10; i++){
        Robot.manipulatorsubsystem.setRightFireMotor(i*10);
        Robot.manipulatorsubsystem.setLeftFireMotor(-i*10);
        WaitCommand(0.2);
      }
      Robot.manipulatorsubsystem.launchSpin = true;
    }*/
      //interval command to be added later
    
    Robot.manipulatorsubsystem.setBottomFireMotor(.3);
    Robot.manipulatorsubsystem.setTopFireMotor(-.3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.manipulatorsubsystem.stopBottomFireMotor();
    Robot.manipulatorsubsystem.stopTopFireMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
