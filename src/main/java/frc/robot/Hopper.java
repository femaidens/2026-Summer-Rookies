package frc.robot;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HopperConstants;
import frc.robot.Port.HopperPort;
import com.ctre.phoenix6.configs.TalonFXConfiguration;



public class Hopper extends SubsystemBase {
private TalonFX indexerMotor; 
private TalonFX hopporMotor;
private RobotContainer RobotContainer;
private HopperConstants HopperConstants;
private Port Port;
private PWMMotorController indexMotor;


public Hopper( ) {
hopperMotor = new TalonFX hopperMotor; 
indexerMotor = new TalonFX indexerMotor;
hopperMotor = new TalconFX(HopperConstants.MOTOR_ID);
indexerMotor = new TalconFX(HopperConstants.MOTOR_ID);
hopperMotor.getConfigurator().apply(TalonFXConfiguration);
indexerMotor.getConfigurator().apply(TalonFXConfiguration);
Port Port  = new Port();
RobotContainer  = new RobotContainer();
Constants = new Constants();



}


public Command runIndexer() {
return this.run(() -> indexerMotor.set(HopperConstants.MOTORSPEED));
}

public Command reverseIndexer() {
return this.run(()-> indexerMotor.set(!HopperConstants.MOTORSPEED ));
}


public Command stopIndexer() {
return this.runOnce(() -> indexMotor.set(0));
	}


public Command runHopperMotor() {
return this.run(() -> hopperMotor.set(HopperConstants.MOTORSPEED));
	}

public Command stopHopperMotor() {
return this.runOnce(() -> hopperMotor.set(0));
	}



public Command reverseHopperMotor() {
return this.run(() -> hopperMotor.set(!HopperConstants.MOTORSPEED));
	}

public void setHopperMotorLimitation(void hopperMotorLimitation) {
	this.hopperMotorLimitation = hopperMotorLimitation;

}
}
 

