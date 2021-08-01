/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Controller abstractions
  private static final int //GAMEPAD_AXIS_LEFT_X = 0, // Gamepad axis
  GAMEPAD_AXIS_LEFT_Y = 1,
  /*GAMEPAD_AXIS_RIGHT_X = 4,
  GAMEPAD_AXIS_RIGHT_Y = 5,
  GAMEPAD_AXIS_LEFT_TRIGGER = 2,
  GAMEPAD_AXIS_RIGHT_TRIGGER = 3,
  GAMEPAD_BUTTON_A = 1, // Gamepad buttons
  GAMEPAD_BUTON_B = 2,
  GAMEPAD_BUTON_X = 3,
  GAMEPAD_BUTTON_Y = 4,
  GAMEPAD_BUTON_LEFT_BUMPER = 5,
  GAMEPAD_BUTON_RIGHT_BUMPER = 6,
  GAMEPAD_BUTTON_BACK = 7,
  GAMEPAD_BUTTON_START = 8,
  GAMEPAD_BUTTON_LOGITECH = 9,
  GAMEPAD_BUTTON_LEFT_JS = 10,
  GAMEPAD_BUTTON_RIGHT_JS = 11,*/
  X3D_AXIS_PITCH = 1, // X3d axis
  X3D_AXIS_ROLL = 0,
  /*X3D_AXIS_YAW = 2,
  X3D_AXIS_OTHER = 3,*/
  X3D_BUTTON_TRIGGER = 1, // X3d buttons
  X3D_BUTTON_GRIP = 2,
  X3D_BUTTON_3 = 3,
  X3D_BUTTON_4 = 4,
  X3D_BUTTON_5 = 5,
  X3D_BUTTON_6 = 6,
  X3D_BUTTON_7 = 7
  //X3D_BUTTON_8 = 8,
  //X3D_BUTTON_9 = 9,
  //X3D_BUTTON_10 = 10,
  //X3D_BUTTON_11 = 11,
  /*X3D_BUTTON_12 = 12*/;


// Controller IDs
public static final int X3D_LEFT_JOYSTICK_ID = 0, // Joysticks IDs
  X3D_RIGHT_JOYSTICK_ID = 1,
  GAMEPAD_JOYSTICK_ID = 2,
  LEFT_TANK_DRIVE_AXIS_ID = X3D_AXIS_PITCH, // Tank drive axis
  RIGHT_TANK_DRIVE_AXIS_ID = X3D_AXIS_PITCH,
  FORWARD_ARCADE_DRIVE_AXIS_ID = X3D_AXIS_PITCH, // Arcade drive axis
  ANGLE_ARCADE_DRIVE_AXIS_ID = X3D_AXIS_ROLL,
  TOGGLE_ARCADE_DRIVE_BUTOON_ID = X3D_BUTTON_5,
  DRIVE_STRAIGHT_BUTTON_ID = X3D_BUTTON_GRIP,
  LAUNCH_BUTTON_ID = X3D_BUTTON_3, //Manipulator buttons
  LOAD_BUTTON_ID = X3D_BUTTON_TRIGGER,
  SEMI_AUTO_BUTTON_ID = X3D_BUTTON_4,
  TEST_BUTTON_ID = X3D_BUTTON_7,
  //PIVOT_BUTTON_ID = X3D_BUTTON_12,
  INTAKE_BUTTON_ID = X3D_BUTTON_6,
  CLIMB_AXIS_ID = GAMEPAD_AXIS_LEFT_Y;

// Drive Subsystem IDs
public static final int DRIVE_LEFT_A_TALON_SRX_ID = 10, 
  DRIVE_LEFT_B_TALON_SRX_ID = 11,
  DRIVE_RIGHT_A_TALON_SRX_ID = 20, 
  DRIVE_RIGHT_B_TALON_SRX_ID = 21,
  DRIVE_LEFT_ENCODER_CHANNELA_ID = 6, 
  DRIVE_LEFT_ENCODER_CHANNELB_ID = 7, 
  DRIVE_RIGHT_ENCODER_CHANNELA_ID = 8, 
  DRIVE_RIGHT_ENCODER_CHANNELB_ID = 9, 
  DRIVE_GYRO_ID = 0,
  DRIVE_FRONT_DISTANCE_PING_ID = 10, 
  DRIVE_FRONT_DISTANCE_ECHO_ID = 11, 
  DRIVE_BACK_DISTANCE_PING_ID = 12, 
  DRIVE_BACK_DISTANCE_ECHO_ID = 13;
public static final double DRIVE_ENCODER_DISTANCE_PER_PULSE  = (15.24 * Math.PI) / 256; // diameter * pi = circumference. circumference / 256 = distance per pulse

// Manipulator Subsystem IDs
public static final int MAN_FIRE_BOTTOM_TALON_SRX_ID = 30,
  MAN_FIRE_TOP_TALON_SRX_ID = 31,
  MAN_LOAD_TALON_SRX_ID = 32,
  MAN_INTAKE_VICTOR_SPX_ID = 33;

// Climb Subsystem IDs
public static final int CLIMBER_ENCODER_CHANNELA_ID = 2, 
  CLIMBER_FRONT_ENCODER_CHANNELB_ID = 3, 
  CLIMBER_TALON_SRX_ID = 30;
//public static final double CLIMBER_ENCODER_DISTANCE_PER_PULSE = ;          tbd

// RIO Post Info
public static int[] expectedTalonIDs = {DRIVE_LEFT_A_TALON_SRX_ID, DRIVE_LEFT_B_TALON_SRX_ID, DRIVE_RIGHT_A_TALON_SRX_ID, 
                DRIVE_RIGHT_B_TALON_SRX_ID};
public static int[] expectedDIOEncoders = {DRIVE_LEFT_ENCODER_CHANNELA_ID,DRIVE_LEFT_ENCODER_CHANNELB_ID, DRIVE_RIGHT_A_TALON_SRX_ID,
                    DRIVE_RIGHT_B_TALON_SRX_ID};
public static int expectedGyro = DRIVE_GYRO_ID;
public static int[] expectedDIOUltrasonic = {DRIVE_FRONT_DISTANCE_PING_ID,DRIVE_FRONT_DISTANCE_ECHO_ID,DRIVE_BACK_DISTANCE_PING_ID,
                       DRIVE_BACK_DISTANCE_ECHO_ID};
}
