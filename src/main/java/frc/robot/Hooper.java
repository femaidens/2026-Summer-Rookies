package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HopperConstants;
import frc.robot.Port.HopperPort;
import com.ctre.phoenix6.configs.TalonFXConfiguration;



public class Hooper extends SubsystemBase {
private TalonFX indexerMotor; 
private TalonFX hopporMotor;
private RobotContainer RobotContainer;
private HopperConstants HopperConstants;
private Port Port;
private PWMMotorController indexMotor;


public Hooper( ) {
hopporMotor = new TalonFX hopporMotor;
indexerMotor = new TalonFX indexerMotor;
hopporMotor = new TalconFX(HopperConstants.MOTOR_ID);
indexerMotor = new TalconFX(HopperConstants.MOTOR_ID);
hopporMotor.getConfigurator().apply(TalonFXConfiguration);
indexerMotor.getConfigurator().apply(TalonFXConfiguration);
Port Port  = new Port();
RobotContainer  = new RobotContainer();
Constants = new Constants();


}


public Command runIndexer() {
return this.run(() -> indexerMotor.set(HopperConstants.MOTORSPEED));
}

public Command reverseIndexer() {
return this.run(()-> indexerMotor.set(!HopperConstants. ));
}


public Command stopIndexer() {
return this.runOnce(() -> indexMotor.set(0));
	}


public Command runHopperMotor() {
return this.run(() -> hopporMotor.set(HopperConstants.MOTOR_SPEED));
	}

public Command stopHopperMotor() {
return this.runOnce(()-> hopporMotor.set(0));
	}



public Command reverseHopperMotor() {
return this.run(() -> hopporMotor.set(!HopperConstants.HOPPERMOTOR_SPEED));
	}
}
 

