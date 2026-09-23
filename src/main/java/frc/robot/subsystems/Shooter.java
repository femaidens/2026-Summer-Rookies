package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Ports;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
//import edu.wpi.first.wpilibj.motorcontrol.TalonFX;
//import edu.wpi.first.wpilibj.motorcontrol.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class Shooter extends SubsystemBase{
	private final TalonFX angleMotor;
	private final TalonFX shooterMotor;
	private final TalonFXConfiguration angleMotorConfig;
	private final TalonFXConfiguration shooterMotorConfig;
	private final DutyCycleEncoder absoluteEncoder;
	private final PIDController PIDController;

public Shooter() {
	angleMotor = new TalonFX(Ports.ShooterPorts.ANGLE_MOTOR, Constants.ShooterConstants.CANBUS);
	shooterMotor = new TalonFX(Ports.ShooterPorts.SHOOTER_MOTOR, Constants.ShooterConstants.CANBUS);
	absoluteEncoder = new DutyCycleEncoder(Ports.ShooterPorts.ENCODER);
	PIDController pid = new PIDController(Constants.IntakeConstants.PIDConstants.kP, Constants.IntakeConstants.PIDConstants.kI, Constants.IntakeConstants.PIDConstants.kD);

	angleMotorConfig = new TalonFXConfiguration();
	angleMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
	angleMotor.getConfigurator().apply(angleMotorConfig);

	shooterMotorConfig = new TalonFXConfiguration();
	shooterMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
	this.PIDController = null;
	shooterMotor.getConfigurator().apply(shooterMotorConfig);
}

public Command setAngle(){
	return this.run(() -> angleMotor.set(Constants.ShooterConstants.SHOOTER_ANGLE));
}

public Command shoot(){
	return this.run(() -> shooterMotor.set(Constants.ShooterConstants.SHOOTER_MOTOR_SPEED));
}

public Command stopShoot(){
	return this.run(() -> shooterMotor.set(0));
}

@Override
public void periodic(){
	SmartDashboard.putNumber("Encoder Position", absoluteEncoder.get());
	SmartDashboard.putNumber("Shooter Motor PWM", shooterMotor.get());
}
}