package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports.HopperPorts;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.RobotContainer;
import frc.robot.Constants.HopperConstants;



public class Hopper extends SubsystemBase {
 

  private final TalonFX indexerMotor;
  private final TalonFX hopperMotor;

  public Hopper() {
    indexerMotor = new TalonFX(HopperPorts.INDEXER_MOTOR, Constants.HopperConstants.canbus);
    hopperMotor = new TalonFX(HopperPorts.HOPPER_MOTOR, Constants.HopperConstants.canbus);
    configureTalonMotor(indexerMotor, Constants.HopperConstants.IndexerMOTOR_CURRENT_Limitation, NeutralModeValue.Brake);
    configureTalonMotor(hopperMotor, Constants.HopperConstants.HopperMOTOR_CURRENT_Limitation, NeutralModeValue.Coast);
  }

  public Command runIndexer() {
    return this.run(() -> indexerMotor.set(Constants.HopperConstants.MOTORFASTSPEED));
  }

  public Command reverseIndexer() {
    return this.run(() -> indexerMotor.set( -Constants.HopperConstants.MOTORFASTSPEED));
  }

  public Command stopIndexer() {
    return this.runOnce(() -> indexerMotor.set(0));
  }

  public Command runHopperMotor() {
    return this.run(() -> hopperMotor.set(Constants.HopperConstants.MOTORSPEED));
  }

  public Command stopHopperMotor() {
    return this.runOnce(() -> hopperMotor.set(0));
  }

  public Command reverseHopperMotor() { 
   return this.run(() -> hopperMotor.set(-Constants.HopperConstants.MOTORSPEED));
  }

  public void runHopper() {
    indexerMotor.set(Constants.HopperConstants.MOTORFASTSPEED);
    hopperMotor.set(Constants.HopperConstants.MOTORSPEED);   //set both motor speed all at the same time
  }

  public Command runHopperCmd() {
    return this.run(() -> runHopper());
  }

  public void reverseHopper() {
    indexerMotor.set(-Constants.HopperConstants.MOTORFASTSPEED);
    hopperMotor.set(-Constants.HopperConstants.MOTORSPEED);   //set both motor speed all at the same time
  }

  public Command reverseHopperCmd() {
    return this.run(() -> reverseHopper());
  }

  public static void configureTalonMotor(TalonFX motor, double currentlimit, NeutralModeValue mode) {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.CurrentLimits.SupplyCurrentLimit = currentlimit;
    config.MotorOutput.NeutralMode = mode;
    motor.getConfigurator().apply(config);
  }
}