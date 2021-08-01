// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.AnalogGyro;
// import edu.wpi.first.wpilibj.BuiltInAccelerometer;
// import edu.wpi.first.wpilibj.Ultrasonic;


// public class SensorsSubsystem extends SubsystemBase {
//   private AnalogGyro driveGyro;
  
//   private BuiltInAccelerometer driveAccel;
  
//   private Ultrasonic frontDriveDistance;
//   private Ultrasonic backDriveDistance;

//   /** Creates a new SensorsSubsystem. */
//   public SensorsSubsystem() {
//     driveGyro = new AnalogGyro(Constants.DRIVE_GYRO_ID);
//     driveAccel = new BuiltInAccelerometer();
//     frontDriveDistance = new Ultrasonic(Constants.DRIVE_FRONT_DISTANCE_PING_ID, Constants.DRIVE_FRONT_DISTANCE_ECHO_ID);
//     backDriveDistance = new Ultrasonic(Constants.DRIVE_BACK_DISTANCE_PING_ID, Constants.DRIVE_BACK_DISTANCE_ECHO_ID);

//     driveGyro.initGyro();
//     driveGyro.calibrate();

//     frontDriveDistance.setEnabled(true);
//     backDriveDistance.setEnabled(true);
//   }

//   // read gyro angle
//   public double getGyroValue(){
//     return driveGyro.getAngle();
//   }

//   // reset gyro
//   public void resetGyro(){
//     driveGyro.calibrate();
//   }

//   //read acceleromter
//   public double getDriveAccelX() { return driveAccel.getX(); }
//   public double getDriveAccelY() { return driveAccel.getY(); }
//   public double getDriveAccelZ() { return driveAccel.getZ(); }

//   // read front and back ultrasonic distances
//   public double getFrontUltrasonicDist() { return frontDriveDistance.getRangeMM() / 10.0; }
//   public double getBackUltrasonicDist() { return backDriveDistance.getRangeMM() / 10.0; }

//   // enable/disable front ultrasonic sensor
//   public void setFrontDriveDistanceEnable(boolean enable) { 
//     frontDriveDistance.setEnabled(enable); 
//   }

//   // enable/disable back ultrasonic sensor
//   public void setBackDriveDistanceEnable(boolean enable){
//     backDriveDistance.setEnabled(enable);
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
