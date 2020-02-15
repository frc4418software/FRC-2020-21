/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ManipulatorSubsystem extends SubsystemBase {
  /**
   * Creates a new ShootSubsystem.
   */
  private WPI_TalonSRX bottomFireMotor;
  private WPI_TalonSRX topFireMotor;
  private WPI_TalonSRX loadMotor;
  private WPI_TalonSRX pivotMotor;
  private WPI_VictorSPX intakeMotor;

  private AnalogPotentiometer pivotPotentiometer;

  private boolean pivotUp = true;

  public boolean launchSpin;

  public ManipulatorSubsystem() {
    bottomFireMotor = new WPI_TalonSRX(Constants.MAN_FIRE_BOTTOM_TALON_SRX_ID);
    topFireMotor = new WPI_TalonSRX(Constants.MAN_FIRE_TOP_TALON_SRX_ID);
    loadMotor = new WPI_TalonSRX(Constants.MAN_LOAD_TALON_SRX_ID);
    intakeMotor = new WPI_VictorSPX(Constants.MAN_INTAKE_VICTOR_SPX_ID);
  }
  //set and get the motors stuff

  //control bottom fire motor
  //right is bottom for now
  public void setBottomFireMotor(double motorValue){
    bottomFireMotor.set(ControlMode.PercentOutput, motorValue);
  }
  public void setTopFireMotor(double motorValue){
    topFireMotor.set(ControlMode.PercentOutput, motorValue);
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
  public void stopBottomFireMotor(){
    bottomFireMotor.set(ControlMode.PercentOutput, 0);
  }
  public void stopTopFireMotor(){
    topFireMotor.set(ControlMode.PercentOutput, 0);
  }
  public void stopLoadMotor(){
    loadMotor.set(ControlMode.PercentOutput, 0);
  }
  public void stopIntakeMotor(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  //read bottom fire motor
  public double getBottomFireMotor(){
    return bottomFireMotor.getMotorOutputPercent();
  }
  public double getTopFireMotor(){
    return topFireMotor.getMotorOutputPercent();
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