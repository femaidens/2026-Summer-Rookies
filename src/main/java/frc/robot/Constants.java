
package frc.robot;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.CANBus;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import com.ctre.phoenix6.CANBus;
//adding
import edu.wpi.first.epilogue.Logged;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
//took these from the constants file 
public final class Constants {
  public static final double MAX_PIVOT_ANGLE = 296;  
  public static final double MIN_PIVOT_ANGLE = 190; 
  public static final int CURRENT_LIMIT = 30;
  public static final double ROLLER_MOTOR_SPEED = 0.5;
  public static final double PIVOT_MOTOR_SPEED = 0.5;
  public static final int DRIVER_PORT = 0;
  public static final int OPERATOR_PORT = 1;

//PID
public static final double K_P = 2;
public static final double K_I = 0;
public static final double K_D = 0;

//place holder setpoint values 
public static final double UP_SETPOINT = 280;
public static final double DOWN_SETPOINT = 200;

public static class IntakeConstants {
    public static final int CURRENT_LIMIT = 30;
    public static final double INTAKE_MOTOR_SPEED = 1.0;
    public static final CANBus CANBUS= new CANBus("rio");
    public static final double PIVOT_SPEED = 0.35;
    public static final double ANGLE_UP = 190; // random values for now until testing
    public static final double ANGLE_DOWN = 296; // same as above
    public static final int kOperatorControllerPort = 1;
    public static final int kDriverControllerPort = 1;

      public static class PIDConstants {
      public static final double kP = 2;
      public static final double kI = 0;
      public static final double kD = 0;
    }
  }

   public static class ShooterConstants{
    public static final int CURRENT_LIMIT = 30;
    public static final double INDEXER_MOTOR_SPEED = 1.0; //0.9
    public static final double SHOOTER_MOTOR_SPEED = -0.55;
    public static final double SHOOTER_CRUISE_SPEED = -0.05;
    public static final double ANGLE_MOTOR_SPEED = 0.1;
    public static final double SHOOTER_ANGLE = 56;

    public static final CANBus CANBUS = new CANBus("rio");
      
      public static class FFConstants {
      public static final double kS = 0.13052;
      public static final double kV = 0.11939;
    }
  }

  public  class HopperConstants {
      
	public static final CANBus canbus = new CANBus("rio");
      public static final double MOTORSPEED= 0.5;
      public static final double MOTORFASTSPEED= 1.0;
      public static final int HopperMOTOR_CURRENT_Limitation = 40;
      public static final int IndexerMOTOR_CURRENT_Limitation = 30;
       
}
      public static class OperatorConstants{
      public static final int DRIVER_PORT = 0;
      public static final int OPERATOR_PORT = 1;
   
}
}

