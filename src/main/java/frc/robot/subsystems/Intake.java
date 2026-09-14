package frc.robot.subsystems;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkAbsoluteEncoder;


public class Intake extends SubsystemBase{

//FIELD 
	private final SparkMax rollerMotor;
	private final SparkMax pivotMotor;
	private final SparkAbsoluteEncoder absoluteEncoder;
	//private final IntakeIO hardware; 
private final PIDController PID = new PIDController(Constants.K_P, Constants.K_I, Constants.K_D);
private double setPoint;
//if we're implementing PID:
private boolean PIDEnabled = false;
//if auton
private boolean autonEnded = false;

public Intake(){
//CONSTRUCTER
rollerMotor = new SparkMax(1, MotorType.kBrushless);
	pivotMotor = new SparkMax(2, MotorType.kBrushless);
absoluteEncoder = pivotMotor.getAbsoluteEncoder();

}

//METHODS
//PIVOT MOTOR: 
//PIVOT HELPER METHODS  
public void helperGoUp(){ 
setSpeed(0.5); 
}

public void helperGoDown(){
setSpeed(-0.5); 
}

public double getAbsolutePosition(){
	return absoluteEncoder.getPosition();
}

public void setPoint(double setPoint) {
    this.setPoint = setPoint;
}

public boolean isPIDEnabled(){
	return PIDEnabled; //cause if it is enabled this value will be true, if it isn't it'll be false
}

//PIVOT SAFETY METHODS:  
//this is the reactive safety measure just in case something unexpected happens    
public boolean safetyCheck(){
if (getAbsolutePosition() > Constants.MAX_PIVOT_HEIGHT){
	helperGoDown(); 
return true;
	}
else if (getAbsolutePosition() < Constants.MIN_PIVOT_HEIGHT){
	helperGoUp();
	return true;
	}
	else{
	return false;
	}
}
//makes this run in the background so it automatically fixes anything at any time
@Override 
public void periodic(){
if (safetyCheck() == true){
return;
}
//if we want PID
if (isPIDEnabled() == true){
	runPID();
}
}

//checks if it can actually go up or down, this is the preventative safety measure so we don't command anything wrong  
public boolean canGoUp() {
    if (getAbsolutePosition() >= Constants.MAX_PIVOT_HEIGHT) {
        return false;
    }
    return true;
}

public boolean canGoDown() {
    if (getAbsolutePosition() <= Constants.MIN_PIVOT_HEIGHT) {
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
    pivotMotor.set(MathUtil.clamp(speed, -0.5, 0.5));
}

//OKAY SO I GET THAT A SIMPLE INTAKE DOESN'T NEED PID, BUT I WAS JUST TRYING TO SEE IF I COULD MAKE ONE. OTHERWISE IGNORE THIS:
public void runPID(){
double currentPosition = getAbsolutePosition();
double PID_Output = PID.calculate(currentPosition, setPoint);  

PID_Output = MathUtil.clamp(PID_Output,-0.5,0.5); 
setSpeed(PID_Output);
}
//AGAIN I'LL PROB LEAVE THIS OUT WHEN I PUSH THE CODE INTO THE REPO, UNLESS U THINK IT NEEDS TO BE HERE

//PIVOT COMMANDABLE METHODS:   
public void goUp(){
	if (canGoUp() == true){
	helperGoUp();
	}
}
public void goDown(){
	if (canGoDown() == true){
	helperGoDown();
	}
}
//these literally just make the pivot move up/down until it physically can't anymore via canGoUp/down

public boolean turnPIDon(){
	PIDEnabled = true;
	return true; 
}

public boolean turnPIDoff(){
	PIDEnabled = false;
	return false;
}

public void goUpToPosition(double amount){
	if (canGoUp() == true){
	setPoint = getAbsolutePosition() + amount;
PIDEnabled = true;
}
}
public void goDownToPosition(double amount){
	if (canGoDown() == true){
	setPoint = getAbsolutePosition() - amount;
PIDEnabled = true;
}
}

//ROLLER MOTOR:    
public void intakeFuel() {
    rollerMotor.set(-0.5);
}

public void ejectFuel() {
    rollerMotor.set(0.5);
}

public void stopRollers() {
    rollerMotor.set(0);
}

//COMMANDS: 
public Command goUpCmd(){
	return this.run(() -> goUp());
	}

public Command goDownCmd(){
	return run(() -> goDown());
	}

public Command stopPivotCmd(){
	return runOnce(() -> stopPivot());
	}

public Command stopRollersCmd(){
	return runOnce(() -> stopRollers());
	}

public Command intakeFuelCmd(){
	return run(() -> intakeFuel());
	}

public Command ejectFuelCmd(){
	return run(() -> ejectFuel());
	}

public Command goUpToPositionCmd(double amount){
	return runOnce(() -> goUpToPosition(amount));
	}

public Command goDownToPositionCmd(double amount){
	return runOnce(() -> goDownToPosition(amount));
	}
//OKAY I ALSO GET WE PROB DON'T NEED STUFF FOR AUTON CAUSE IN THE ACTUAL GAME IM ASSUMING WE'LL FILL UP THE ROBOT WITH BALLS FROM OUR CHAMBER TO SHOOT INSTEAD OF INTAKING THEM, BUT JUST IN CASE I WANTED TO CHECK IF THIS MAKES SENSE: 

//helper methods
public void startAuton(){
    autonEnded = false;
}

public void endAuton(){
    autonEnded = true;
}

public boolean isAutonOn(){
return !autonEnded; //cause again if this is true method returns true if false it returns false 
}

public boolean checkForFuel(){
	if (//camera spots fuel//ignore this part idk how the camera works, and I already know that we aren't using a camera for our intake so double emphasis on this being theoretical//){
		return true;
	}
	else{
		return false;
}
}

//commands   
public Command endAutonCmd(){
	return runOnce(()-> {
	endAuton(); 
	});
}

public Command autonFuelIntake(){	
return run(()-> {
if (isAutonOn() && checkForFuel() == true){
intakeFuel();
}
else{
stopRollers();
}

});

}
