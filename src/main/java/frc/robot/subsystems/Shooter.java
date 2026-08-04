package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Ports;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj.motorcontrol.TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.TalonFXConfiguration;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.math.controller.PIDController;

public class Shooter extends SubsystemBase{
	private final TalonFX angleMotor;
	private final TalonFX shooterMotor;
	private final DutyCycleEncoder absoluteEncoder;
	private final PIDController PIDController;
}

public Shooter {
	angleMotor = new TalonFX(Ports.angleMotor);
	shooterMotor = new TalonFX(Ports.shooterMotor);
	absoluteEncoder = new AbsoluteEncoder(Ports.absoluteEncoder);
	PIDController pid = new PIDController(Constants.PIDConstants.kP, Constants.PIDConstants.kI, Constants.PIDConstants.kD);

	angleMotorConfig = new TalonFXConfiguration
	angleMotorConfig.NeutralMode = Brake;

	shooterMotorConfig = new TalonFXConfiguration
	shooterMotorConfig.NeutralMode = Coast;
}

public Command setAngle(){
	return this.run(() -> angleMotor.setAngle(Constants.ShooterConstants.shootAngle));
}

public Command shoot(){
	return this.run(() -> shooterMotor.setSpeed(Constants.ShooterConstants.SHOOTER_MOTOR_SPEED));
}

public double getEncoder(){
	return absoluteEncoder.getPosition();
}

public double getShooterMotorSpeed(){
	return shooterMotor.getSpeed();
}

@Override
SmartDashboard absoluteEncoder.getEncoder();
SmartDashboard shooterMotor.getShooterMotorSpeed();