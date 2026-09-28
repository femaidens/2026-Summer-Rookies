// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final Intake intake = new Intake(); 
  
  private final CommandXboxController operJoy =
      new CommandXboxController(OperatorConstants.OPERATOR_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  
        operJoy.povRight().whileTrue(intake.goUpCmd());
        operJoy.povLeft().whileTrue(intake.goDownCmd());
        operJoy.povRight().onTrue(intake.goUpToPositionCmd()).whileFalse(intake.stopPivotCmd());
        operJoy.povLeft().onTrue(intake.goDownToPositionCmd()).whileFalse(intake.stopPivotCmd());
        operJoy.b().whileTrue(intake.intakeFuelCmd()).whileFalse(intake.stopRollersCmd());
        operJoy.leftBumper().whileTrue(intake.ejectFuelCmd());       

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }
  //public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
  //}
}
