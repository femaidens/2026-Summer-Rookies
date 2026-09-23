// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
package frc.robot;
//took these from the constants file 
public final class Constants{
public static final double MAX_PIVOT_ANGLE = 296;  
public static final double MIN_PIVOT_ANGLE = 190; 
public static final int CURRENT_LIMIT = 30;
public static final double ROLLER_MOTOR_SPEED = 0.5;
public static final double PIVOT_MOTOR_SPEED = 0.5;
//PID
public static final double K_P = 2;
public static final double K_I = 0;
public static final double K_D = 0;

//place holder setpoint values 
public static final double UP_SETPOINT = 280;
public static final double DOWN_SETPOINT = 200;

public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

}

