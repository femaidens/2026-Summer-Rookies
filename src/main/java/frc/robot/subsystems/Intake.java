//So what is the function of the intake in the 2026 FRC robotics game REBUILT?
//Function
// 1) collect fuel foam pieces from the floor as fast as possible 
	// 2) feed fuel pieces into the spindexer as smoothly and fast as possible
// 3)  Have control over the pieces so other robots can't steal or disrupt the intake process 
//How it works   
	// 1) Intake is at resting position above ground
	// 2) Driver presses down button
	// 3) Intake pivots down   
	// 4) Driver presses intake button  
	// 5) Intake spins green compliant wheels 
	// 6) Balls are pulled/sucked into intake    
	// 7) Balls move up ramp into hopper (then spindexer)
	// 8) Driver presses up button  
	// 9) Intake pivots back up to resting position 
//Mechanisms involved:  
	// Pivot motor
	// Wheel roller motor
	//Absolute encoder measures intake angle
	// That's all I can think of so far bc I think the pully-shaft stuff is just like automatically happening with the motors like hardware integrated or sum

//What methods would there have to be?
	//pivot motor 0.5 to spin up
//pivot motor - 0.5 to spin down
//roller motor -0.5 to spin wheels backward and intake balls
//roller motor 0.5 to spin wheels forward and eject clam clutter
//method to put roller motor at 0 to stop spinning   
//method to stop pivot motor, leave it at rest  
//method to make sure pivot is not going too up or down than possible
//set pivot angle PID
//set pivot speed anything
//All of this would be seperate methods the driver would control in teleop.  
//In auton, you'd have one method that forms a controlled loop of the other methods so they all run in progression and depending on different factors everything gets executed automatically.

//CODE:  
public class Intake extends SubsystemBase{

	//FIELD 
		private final SparkMax rollerMotor;
		private final SparkMax pivotMotor;
		private final AbsoluteEncoder absoluteEncoder;
	
	public Intake(){
	//CONSTRUCTER
	rollerMotor = new SparkMax(1);
		pivotMotor = new SparkMax(2);
		absoluteEncoder = pivotMotor.getAbsoluteEncoder(2);
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
	
	//PIVOT SAFETY METHODS: 
	//this is the reactive safety measure just in case something unexpected happens    
	public void safetyCheck(){
		if (getAbsolutePosition() > MAX_PIVOT_HEIGHT){
		helperGoDown(); 
		}
	else if (getAbsolutePosition() < MIN_PIVOT_HEIGHT){
		helperGoUp();
		}
	}
	@Override 
	public void periodic(){
		safetyCheck();
	}
	//checks if it can actually go up or down, this is the preventative safety measure so we don't command anything wrong  
	public boolean canGoUp() {
		if (getAbsolutePosition() >= MAX_PIVOT_HEIGHT) {
			return false;
		}
		return true;
	}
	
	public boolean canGoDown() {
		if (getAbsolutePosition() <= MIN_PIVOT_HEIGHT) {
			return false;
		}
		return true;
	}
	
	public void stopPivot(){
	pivotMotor.set(0.0);
	}
	
	//PIVOT CONVENIENCE METHODS  
	public void setSpeed(double speed) {
		pivotMotor.set(MathUtil.clamp(speed, -0.5, 0.5));
	}
	//temporary PID setup (pseudocode so i'll make it more technical later)
	public void goToExactPosition(double setAngle){
	  if (getAbsolutePosition() > setAngle){
	helperGoDown();
	}
	  else if (getAbsolutePosition() < setAngle){
	helperGoUp();
	}
	  else if (getAbsolutePosition() == setAngle){
		setSpeed(0);
		}
	}
	
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
	//ROLLER MOTOR 
	
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
		return run(() -> intake.goUp());
		}
	
	public Command goDownCmd(){
		return run(() -> intake.goDown());
		}
	
	public Command stopPivotCmd(){
		return run(() -> intake.stopPivot());
		}
	
	public Command intakeFuelCmd(){
		return run(() -> intake.intakeFuel());
		}
	
	public Command ejectFuelCmd(){
		return run(() -> intake.ejectFuel());
		}
	//AUTO idk if we need to do this but maybe 
	//command it to intake if we sense a ball 
	//command it to eject if its too full
	//command it to go up if we are intaking
	//command it to go down if we are not intaking anymore 
	