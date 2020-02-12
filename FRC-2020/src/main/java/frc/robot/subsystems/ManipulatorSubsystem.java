/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ManipulatorSubsystem extends SubsystemBase {
  /**
   * Creates a new ShootSubsystem.
   */
  private WPI_TalonSRX rightFireMotor;
  private WPI_TalonSRX leftFireMotor;
  private WPI_TalonSRX loadMotor;
  private WPI_TalonSRX pivotMotor;
  private WPI_TalonSRX intakeMotor;

  private AnalogPotentiometer pivotPotentiometer;

  private boolean pivotUp = true;

  public boolean launchSpin;

  public ManipulatorSubsystem() {
    rightFireMotor = new WPI_TalonSRX(Constants.MAN_FIRE_RIGHT_TALON_SRX_ID);
    leftFireMotor = new WPI_TalonSRX(Constants.MAN_FIRE_LEFT_TALON_SRX_ID);
    loadMotor = new WPI_TalonSRX(Constants.MAN_LOAD_TALON_SRX_ID);
    intakeMotor = new WPI_TalonSRX(Constants.MAN_INTAKE_TALON_SRX_ID);
  }
  //set and get the motors stuff

  //control right spinning fire motor
  public void setRightFireMotor(double motorValue){
    rightFireMotor.set(ControlMode.PercentOutput, motorValue);
  }
  public void setLeftFireMotor(double motorValue){
    leftFireMotor.set(ControlMode.PercentOutput, motorValue);
  }
  public void setLoadMotor(double motorValue){
    loadMotor.set(ControlMode.PercentOutput, motorValue);
  }
  public void setIntakeMotor(double motorValue){
    intakeMotor.set(ControlMode.PercentOutput, motorValue);
  }
  public void setPivotMotor(double motorValue){
    pivotMotor.set(ControlMode.PercentOutput, motorValue);
  }

  //stop the motors
  public void stopRightFireMotor(){
    rightFireMotor.set(ControlMode.PercentOutput, 0);
  }
  public void stopLeftFireMotor(){
    leftFireMotor.set(ControlMode.PercentOutput, 0);
  }
  public void stopLoadMotor(){
    loadMotor.set(ControlMode.PercentOutput, 0);
  }
  public void stopIntakeMotor(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  //read right spinning fire motor
  public double getRightFireMotor(){
    return rightFireMotor.getMotorOutputPercent();
  }
  public double getLeftFireMotor(){
    return leftFireMotor.getMotorOutputPercent();
  }
  public double getLoadMotor(){
    return loadMotor.getMotorOutputPercent();
  }
  public double getIntakeMotor(){
    return intakeMotor.getMotorOutputPercent();
  }
  public double getPivotMotor(){
    return pivotMotor.getMotorOutputPercent(); 
  }
  
  // get whether the pivot is up
  public boolean pivotIsUp(){
    if( getPivotPotentiometer() > 13){
      return pivotUp;
    }
    else{
      return !pivotUp;
    }
  }
  

  //Potentiometer stuffs (I hope this works)
  //read potentiometer
  public double getPivotPotentiometer(){
    return pivotPotentiometer.get();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}