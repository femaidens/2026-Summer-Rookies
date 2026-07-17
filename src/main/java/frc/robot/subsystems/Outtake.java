package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Outtake extends SubsystemBase {
    private final SparkMax motor;
    private final DigitalInput frontBB;
    private final DigitalInput middleBB;
    private final DigitalInput backBB;

    public Outtake() {
        motor = new SparkMax(13, null);

        frontBB = new DigitalInput(2);
        middleBB = new DigitalInput(3);
        backBB = new DigitalInput(4);

    }
    
    public Command runMotorCmd() {
        return this.run(() -> motor.set(0.9));
    }

    public Command reverseMotorCmd() {
        return this.run(() -> motor.set(-0.9));
    }

    public Command stopMotorCmd() {
        return this.runOnce(() -> motor.set(0));
    }

    public boolean isFrontBBBroken() {
        return !frontBB.get();
    }

    public boolean isMiddleBBBroken() {
        return !middleBB.get();
    }

    public boolean isBackBBBroken() {
        return !backBB.get();
    }

    public boolean isCoral() {
        return !isMiddleBBBroken() && !isBackBBBroken();
    }

}