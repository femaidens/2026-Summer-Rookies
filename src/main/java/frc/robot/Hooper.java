package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class Hooper extends SubsystemBase {

private final Talon indexerMotor; 
private final  Talon hopporMotor;

public Hooper( ) {
hopperMotor = new TalonFX hopporMotor;
indexerMotor = new TalonFX indexerMotor;
hopperMotor = new TalconFX(HopperConstants.MOTOR_ID);
indexerMotor = new TalconFX(HopperConstants.MOTOR_ID);
hopperMotor.getConfigurator().apply(TalonFXConfiguration);
indexerMotor.getConfigurator().apply(TalonFXConfiguration);
Port Port  = new Port();
RobotContainer  = new RobotContainer();

}


public Command runIndexer() {
return this.run(()-> ) {
indexerMotor.set(HopperConstants.INDEXERMOTOR_SPEED);
}
}
public Command reverseIndexer() {
return this.run(()-> ) {
indexerMotor.set(!HopperConstants.MOTOR_SPEED );
}


public Command stopIndexer() {
return this.run(()->){
indexerMotor.set ();
	}
}

public Command runHopperMotor() {
return this.run(() -> ) {
hopperMoter.set(HopperConstants.MOTOR_SPEED);
	}
}

public Command stopHopperMotor() {
return this.run(()->) {
hopperMotor.set();
	}
}


public Command reverseHopperMotor() {
return this.run((!HopperConstants.HOPPERMOTOR_SPEED) -> ) {
hopperMoter.set();
	}
}

 }
}

