package frc.robot.subsystems;

import java.lang.module.Configuration;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private final TalonFX leaderMotor;
    private final TalonFX followerMotor;
    private final DigitalInput limitswitch;
    private final TalonFXConfiguration leaderMotorConfig;
    private final TalonFXConfiguration followerMotorConfig;
    private final PIDController pid;

    public Climb() {

        leaderMotor = new TalonFX(0);
        followerMotor = new TalonFX(1);   
        limitswitch= new DigitalInput(2);   
        pid = new PIDController(0.1, 0, 0.2);
        //configs
        leaderMotorConfig = new TalonFXConfiguration();
        leaderMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        leaderMotor.getConfigurator().apply(leaderMotorConfig);

        followerMotorConfig = new TalonFXConfiguration();
        followerMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        followerMotor.getConfigurator().apply(followerMotorConfig);
    
    }

    public void setSpeed(){
        leaderMotor.set(0.5);
        followerMotor.set(0.5);
    }

    public void reverseSpeed(){
        leaderMotor.set(-0.5);
        followerMotor.set(-0.5);
    }
    
    public void stop(){
        leaderMotor.set(0.0);
        followerMotor.set(0.0);
    }    

    public boolean getLimitswitch(){
        return limitswitch.get();
    }

    public void upClimb(){
        if (getLimitswitch()){
            stop();
        }
    }

    public void climbUpPID(double setpoint){
        leaderMotor.setVoltage(pid.calculate(leaderMotor.getPosition().getValueAsDouble(), setpoint));
    }

    public void climbDownPID(double setpoint){
        followerMotor.setVoltage(pid.calculate(followerMotor.getPosition().getValueAsDouble(), setpoint));
    }

    //COMMANDS

    public Command upClimbCmd(){
       return this.run(() -> upClimb());
    }

    public Command stopCmd(){
       return this.run(() -> stop());
    }

    public Command reverseSpeedCmd(){
       return this.run(() -> reverseSpeed());
    }

    public Command setSpeedCmd(){
       return this.run(() -> setSpeed());
    }

}
