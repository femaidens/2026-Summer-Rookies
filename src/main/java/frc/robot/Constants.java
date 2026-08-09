
package frc.robot;
import com.ctre.phoenix6.CANBus;

public  class Constants {

      public  class HopperConstants {
      
	public static final CAN canbus = new CANBus("rio");
      private static final double MOTOR_SPEED = 0.5;
      private static final int HopperMOTOR_SPEED_Limitation  = 40;
      private static final int IndexerMOTOR_SPEED_Limitation  = 30;
       
}
      public static class OperatorConstants{
      public static final int DRIVER_PORT = 0;
      public static final int OPERATOR_PORT = 1;

}
}



