package frc.robot.subsystems;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import com.ctre.phoenix6.configs.TalonFXConfiguration; 
import com.ctre.phoenix6.hardware.TalonFX; 
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DutyCycleEncoder;  

public class Intake extends SubsystemBase{

//FIELD 
	private final TalonFX rollerMotor;
	private final TalonFX pivotMotor;
	private final DutyCycleEncoder absoluteEncoder;
	//PID; 
private final PIDController PID;

public Intake(){
//CONSTRUCTER
//just used what was in this link idk if most of it applies
//TalonFXConfiguration (CTRE Phoenix 6 Java 26.3.0)    
//makes the objects
rollerMotor = new TalonFX(1);
	pivotMotor = new TalonFX(2);
absoluteEncoder = new DutyCycleEncoder(3);
PID = new PIDController(Constants.K_P, Constants.K_I, Constants.K_D);
//add configs to them
	TalonFXConfiguration rollerConfig = new TalonFXConfiguration();
	TalonFXConfiguration pivotConfig = new TalonFXConfiguration();
//maybe set current limit
rollerConfig.CurrentLimits.StatorCurrentLimitEnable = true;
rollerConfig.CurrentLimits.StatorCurrentLimit = Constants.CURRENT_LIMIT;
pivotConfig.CurrentLimits.StatorCurrentLimitEnable = true;
pivotConfig.CurrentLimits.StatorCurrentLimit = Constants.CURRENT_LIMIT;
//what happens when roller intake gotta stop rolling? 
rollerConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake; 
pivotConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake; 
//apply configs
pivotMotor.getConfigurator().apply(pivotConfig);
rollerMotor.getConfigurator().apply(rollerConfig);

}

//METHODS
//1) PIVOT MOTOR: 
//PIVOT HELPER METHODS  
public void helperGoUp(){ 
setSpeed(Constants.PIVOT_MOTOR_SPEED); 
}

public void helperGoDown(){
setSpeed(-Constants.PIVOT_MOTOR_SPEED); 
}

public double getAbsolutePosition(){
	return absoluteEncoder.get();
}

//PIVOT SAFETY METHODS:   
//this is the reactive safety measure just in case something unexpected happens    
public boolean safetyCheck(){
if (getAbsolutePosition() > Constants.MAX_PIVOT_ANGLE){
		runPID(Constants.UP_SETPOINT);
return true;
	}
if (getAbsolutePosition() < Constants.MIN_PIVOT_ANGLE){
		runPID(Constants.DOWN_SETPOINT);
		return true;
	}
	else{
		return false;
	}
}
//makes this run in the background so it automatically fixes anything at any time

//checks if it can actually go up or down, this is the preventative safety measure so we don't command anything wrong     
public boolean canGoUp() {
    if (getAbsolutePosition() >= Constants.MAX_PIVOT_ANGLE) {
        return false;
    }
    return true;
}

public boolean canGoDown() {
    if (getAbsolutePosition() <= Constants.MIN_PIVOT_ANGLE) {
        return false;
    }
    return true;
}

//emergency stop
public void stopPivot(){
pivotMotor.set(0.0);
}

//PIVOT CONVENIENCE METHODS   
//if the driver wants to set it to a specific speed, here it is
public void setSpeed(double speed) {
    pivotMotor.set(MathUtil.clamp(speed, -Constants.PIVOT_MOTOR_SPEED, Constants.PIVOT_MOTOR_SPEED));
}
//PID 
public void runPID(double setPoint){
double currentPosition = getAbsolutePosition();
double PID_Output = PID.calculate(currentPosition,setPoint);
setSpeed(PID_Output);  
}

//PIVOT COMMANDABLE METHODS:   
//automatic
public void goUpToPosition(){
	if (canGoUp() == true){
	runPID(Constants.UP_SETPOINT);
	}
}
public void goDownToPosition(){
	if (canGoDown() == true){
	runPID(Constants.DOWN_SETPOINT);
	}
}
//idk why anyone'd wanna do manual but this is it (idk how to make it stop when the button is pressed) 
public void goUp(){
	if (canGoUp() == true){
		helperGoUp();
	}
	else{
		stopPivot();
	}
}
public void goDown(){
	if (canGoDown() == true){
		helperGoDown();
	}
	else{
		stopPivot();
	}
}
//ROLLER MOTOR:     
public void intakeFuel() {
    rollerMotor.set(Constants.ROLLER_MOTOR_SPEED);
}

public void ejectFuel() {
    rollerMotor.set(-Constants.ROLLER_MOTOR_SPEED);
}

public void stopRollers() {
    rollerMotor.set(0);
}

//COMMANDS: 
public Command goUpCmd(){
	return this.run(() -> goUp());
	}

public Command goDownCmd(){
	return this.run(() -> goDown());
	}

public Command goUpToPositionCmd(){
	return this.run(() -> goUpToPosition());
	}

public Command goDownToPositionCmd(){
	return this.run(() -> goDownToPosition());
	}

public Command stopPivotCmd(){
	return this.runOnce(() -> stopPivot());
	}

public Command stopRollersCmd(){
	return this.runOnce(() -> stopRollers());
	}

public Command intakeFuelCmd(){
	return this.run(() -> intakeFuel());
	}

public Command ejectFuelCmd(){
	return this.run(() -> ejectFuel());
	}
}

@Override 
public void periodic(){
safetyCheck();
}
//okay I really don't like how override and regular go to this point both command the pivot motor (AND use the same PID method) and since one is periodic wouldn't that cause conflicts??

// in the commands how do i make it so the manual up/down for pivot and intake/eject movements stop when the button is released vs pressed?
