
package frc.robot;
import com.ctre.phoenix6.CANBus;

public  class Constants {

      public  class HopperConstants {
      
	public static final CANBus canbus = new CANBus("rio");
      public static final double MOTORSPEED= 0.5;
      public static final int HopperMOTOR_SPEED_Limitation = 40;
      public static final int IndexerMOTOR_SPEED_Limitation = 30;
       
}
      public static class OperatorConstants{
      public static final int DRIVER_PORT = 0;
      public static final int OPERATOR_PORT = 1;

}
}



