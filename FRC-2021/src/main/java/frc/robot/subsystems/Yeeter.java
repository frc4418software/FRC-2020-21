// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Yeeter extends SubsystemBase {
  private String statusTabName = "Yeeter Interface";

  private double frontLauncherVal, backLauncherIn, loaderIn, intakeIn;
  frontLauncherVal = backLauncherIn = loaderIn = intakeIn = 0.0;

  /** Creates a new Yeeter. */
  public Yeeter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
