package frc.robot.subsystems;

import java.lang.module.Configuration;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private final TalonFX leaderMotor;
    private final TalonFX followerMotor;
    private final DigitalInput limitswitch;
    private final Configuration leaderMotorConfig;
    private final Configuration followerMotorConfig;

    public Climb() {

        leaderMotor = new TalonFX(0);
        followerMotor = new TalonFX(1);   
        limitswitch= new DigitalInput(2);     
        //configs
        leaderMotorConfig =
    }

    public void setSpeed(){
        leaderMotor.setSpeed(Constants.SPEED);
        followerMotor.setSpeed(Constants.SPEED);
    }

    public void reverseSpeed(){
        leaderMotor.setSpeed(-Constants.SPEED);
        followerMotor.setSpeed(-Constants.SPEED);
    }
    
    public void stop(){
        leaderMotor.setSpeed(0.0);
        followerMotor.setSpeed(0.0);
    }    

    public boolean getLimitswitch(){
        limitswitch.get();
    }

    public void upClimb(){
        if (getLimitswitch()){
            reverseSpeed();
        }
    }

    //COMMANDS

    public Command upClimbCmd(){
        this.run(()-
    }

}
