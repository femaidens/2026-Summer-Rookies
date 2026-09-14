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
	//makes this run in the background so it automatically fixes anything at any time
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
	//emergency stop
	public void stopPivot(){
	pivotMotor.set(0.0);
	}
	
	//PIVOT CONVENIENCE METHODS  
	//if the driver wants to set it to a specific speed, here it is
	public void setSpeed(double speed) {
		pivotMotor.set(MathUtil.clamp(speed, -0.5, 0.5));
	}
	//temporary PID setup (it's pseudocode so i'll make it more accurate later)
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
	