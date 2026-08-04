package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hooper extends SubsystemBase {

private final TalonFX indexerMotor; 
private final  TalonFX hopporMotor;

public Hopper() {
shooterMotor = new  TalonFX hopporMotor;
indexerMotor = new TalonFX indexerMotor;
hopperMotor = new TalconFX(HopperConstants.MOTOR_ID);
indexerMotor = new TalconFX(HopperConstants.MOTOR_ID);
hopperMotor.getConfigurator().apply(TalonFXConfiguration);
indexerMotor.getConfigurator().apply(TalonFXConfiguration);

}


public Command runIndexer() {
return this.run(()-> ) {
indexerMotor.set(HopperConstants.INDEXERMOTOR_SPEED);
}
}
public Command reverseIndexer() {
return this.run(()-> ) {
indexerMotor.set(!HopperConstants.INDEXERMOTOR_SPEED );
}


public Command stopIndexer() {
return this.run(()->){
indexerMotor.set ();
	}
}

public Command runHopperMotor() {
return this.run(() -> ) {
hopperMoter.set(HopperConstants.HOPPERMOTOR_SPEED);
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

