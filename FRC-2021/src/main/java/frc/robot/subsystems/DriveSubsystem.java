/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.teamlibraries.DriveInputPipeline;


public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonSRX leftDriveMotorA;
  private WPI_TalonSRX leftDriveMotorB;
  private WPI_TalonSRX rightDriveMotorA;
  private WPI_TalonSRX rightDriveMotorB;
  private DifferentialDrive robotDrive;
  private boolean arcadeDrive = true;
  
  private Encoder leftDriveEncoder;
  private Encoder rightDriveEncoder;

  public DriveSubsystem() {
    // DRIVE MOTORS
    leftDriveMotorA = new WPI_TalonSRX(Constants.DRIVE_LEFT_A_TALON_SRX_ID);
    leftDriveMotorB = new WPI_TalonSRX(Constants.DRIVE_LEFT_B_TALON_SRX_ID);
    rightDriveMotorA = new WPI_TalonSRX(Constants.DRIVE_RIGHT_A_TALON_SRX_ID);
    rightDriveMotorB = new WPI_TalonSRX(Constants.DRIVE_RIGHT_B_TALON_SRX_ID);

    leftDriveMotorB.follow(leftDriveMotorA);
    rightDriveMotorB.follow(rightDriveMotorA);

    robotDrive = new DifferentialDrive(leftDriveMotorA, rightDriveMotorA);

    leftDriveEncoder = new Encoder(Constants.DRIVE_LEFT_ENCODER_CHANNELA_ID, Constants.DRIVE_LEFT_ENCODER_CHANNELB_ID);
    rightDriveEncoder = new Encoder(Constants.DRIVE_RIGHT_ENCODER_CHANNELA_ID, Constants.DRIVE_RIGHT_ENCODER_CHANNELB_ID);

    brakeCoastLeftMotors(false);
    brakeCoastRightMotors(false);

    // ENCODERS
    leftDriveEncoder.setDistancePerPulse(Constants.DRIVE_ENCODER_DISTANCE_PER_PULSE);
    rightDriveEncoder.setDistancePerPulse(Constants.DRIVE_ENCODER_DISTANCE_PER_PULSE);
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }

  // set both left motors
  public void setLeftMotors(double negToPosPercentage){
    leftDriveMotorA.set(ControlMode.PercentOutput, negToPosPercentage);
  }

  // set both right motors
  public void setRightMotors(double negToPosPercentage){
    rightDriveMotorA.set(ControlMode.PercentOutput, negToPosPercentage);
  }

  // read shared left motors' percentage
  public double getLeftMotors(){
    return leftDriveMotorA.getMotorOutputPercent();
  }

  // read shared right motors' percentage
  public double getRightMotors(){
    return rightDriveMotorA.getMotorOutputPercent();
  }

  // brake or coast left motors with boolean (true for braking)
  public void brakeCoastLeftMotors(boolean isBraking) {
    if (isBraking) {
      leftDriveMotorA.setNeutralMode(NeutralMode.Brake);
      leftDriveMotorB.setNeutralMode(NeutralMode.Brake);
    } else {
      leftDriveMotorA.setNeutralMode(NeutralMode.Coast);
      leftDriveMotorB.setNeutralMode(NeutralMode.Coast);
    }
  }

  // brake or coast right motors with boolean (true for braking)
  public void brakeCoastRightMotors(boolean isBraking) {
    if (isBraking) {
      rightDriveMotorA.setNeutralMode(NeutralMode.Brake);
      rightDriveMotorB.setNeutralMode(NeutralMode.Brake);
    } else {
      rightDriveMotorA.setNeutralMode(NeutralMode.Coast);
      rightDriveMotorB.setNeutralMode(NeutralMode.Coast);
    }
  }

  // Automatically set the breaks on when the robot is not moving
  // and disable them when the robot is moving
  public void autoBreakTankDrive(double[] values) {
    // if the input is 0, set break, else don't
    if (values[0] == 0.0) {
      brakeCoastLeftMotors(true);
    } else {
      brakeCoastLeftMotors(false);
    }

    if (values[1] == 0.0) {
      brakeCoastRightMotors(true);
    } else {
      brakeCoastRightMotors(false);
    }
  }

  //drive both motors at once
  public void tankDrive(double leftValue, double rightValue){
    robotDrive.tankDrive(leftValue, rightValue);
  }

  // tank drive wrapper for double arrays
  public void tankDrive(double[] values) { tankDrive(values[0], values[1]); }

  // standard arcade drive with directional toggle
  public void arcadeDrive(double forwardValue, double angleValue) {
    robotDrive.arcadeDrive(-forwardValue, angleValue);
  }

  // arcade drive wrapper for double arrays
  public void arcadeDrive(double[] values) { arcadeDrive(values[0], values[1]); }

  // stop driving
  public void stopDrive(){
    leftDriveMotorA.set(ControlMode.PercentOutput, 0);
    rightDriveMotorA.set(ControlMode.PercentOutput, 0);
  }

  // a wrapper around tank drive that sets stuff up to be better optimized for teleop control
  public void teleopTankDriveWrapper(double leftValue, double rightValue) {
    // Convert to an array to allow for easy data transmission
    double[] values = {leftValue, rightValue};

    // do fancy array manipulation stuffs
    DriveInputPipeline dip = new DriveInputPipeline(values);
    dip.inputMapWrapper(DriveInputPipeline.InputMapModes.IMM_SQUARE);
    dip.magnetizeTankDrive();
    dip.applyDeadzones();
    values = dip.getValues();

    autoBreakTankDrive(values);

    // use the modified arrays to drive the robot
    tankDrive(values);
  }

  // a wrapper around arcade drive that sets stuff up to be better optimized for teleop controll
  public void teleopArcadeDriveWrapper(double forwardValue, double angleValue) {
    // Convert to an array to allow for easy data transmission
    double[] values = {forwardValue, angleValue};

    // do fancy array manipulation stuffs
    /*values = inputMapWrapper(values, InputMapModes.IMM_SQUARE, InputMapModes.IMM_SQUARE);
    values = deadzoneTankDrive(values);*/
    DriveInputPipeline dip = new DriveInputPipeline(values);
    dip.inputMapWrapper(DriveInputPipeline.InputMapModes.IMM_CUBE, DriveInputPipeline.InputMapModes.IMM_CUBE);
    dip.applyDeadzones();
    values = dip.getValues();
    
    autoBreakTankDrive(dip.convertArcadeDriveToTank(values));

    // use the modified arrays to drive the robot
    arcadeDrive(values);
  }

  // set the robot to arcade drive or not
  public void setArcadeDrive(boolean mode) { arcadeDrive = mode; }

  // get whether the robot is in arcade drive mode or not
  public boolean isArcadeDrive() { return arcadeDrive; }

  //read left encoder
  public double getLeftDriveEncoder() { return -leftDriveEncoder.getDistance(); }

  //read right encoder
  public double getRightDriveEncoder() { return rightDriveEncoder.getDistance(); }

  public double getDistance() {
    return (getRightDriveEncoder() + getLeftDriveEncoder()) / 2.0;
  }

  //reset left encoder
  public void resetLeftDriveEncoder() { leftDriveEncoder.reset(); }

  //reset right encoder
  public void resetRightDriveEncoder() { rightDriveEncoder.reset(); }

  //reset both encoders
  public void resetEncoders() { resetLeftDriveEncoder(); resetRightDriveEncoder(); }

  @Override
  public void periodic() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TeleopDriveCommand());
  }
}
