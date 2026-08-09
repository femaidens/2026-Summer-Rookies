package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Port.HopperPort;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;



public class Hopper extends SubsystemBase private final TalonFX indexerMotor; 
private final TalonFX hopperMotor;
private RobotContainer RobotContainer;
private Constants HopperConstants;
private Port Port;



public Hopper( ) {
TalonFXConfiguration config = new TalonFXConfiguration();
indexerMotor = new TalonFX(HopperPort.INDEXER_MOTOR, HopperConstants.canbus);
hopperMotor= new TalonFX(HopperPort.HOPPER_MOTOR, HopperConstants.canbus);
hopperMotor.getConfigurator().apply(TalonFXConfiguration);
indexerMotor.getConfigurator().apply(TalonFXConfiguration);
Port Port  = new Port();
RobotContainer  = new RobotContainer();
HopperConstants = new HopperConstants();

}


public Command runIndexer() {
return this.run(() -> indexerMotor.set(HopperConstants.MOTOR_SPEED));
}

public Command reverseIndexer() {
return this.run(()-> indexerMotor.set(-HopperConstants.MOTOR_SPEED ));
}


public Command stopIndexer() {
return this.runOnce(() -> indexerMotor.set(0));
	}


public Command runHopperMotor() {
return this.run(() -> hopperMotor.set(HopperConstants.MOTOR_SPEED));
	}

public Command stopHopperMotor() {
return this.runOnce(() -> hopperMotor.set(0));
	}



public Command reverseHopperMotor() {
return this.run(() -> hopperMotor.set(-HopperConstants.MOTOR_SPEED));
	}


}
 

