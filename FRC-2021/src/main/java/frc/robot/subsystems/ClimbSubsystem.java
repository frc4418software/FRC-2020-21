/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;


public class ClimbSubsystem extends SubsystemBase {
  private WPI_TalonSRX climbMotor;

  private Encoder climbEncoder;
  
  public ClimbSubsystem() {
    climbMotor = new WPI_TalonSRX(Constants.CLIMBER_TALONSRX_ID);
  }

  public void SetClimb(double motorValue) {
    climbMotor.set(ControlMode.PercentOutput, motorValue);
  }

  public Encoder getClimbEncoder() { return climbEncoder; }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
