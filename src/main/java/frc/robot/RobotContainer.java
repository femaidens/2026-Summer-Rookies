
package frc.robot;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Hopper;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Constants;

public class RobotContainer {
  private final Intake intake = new Intake(); 
  
  private final CommandXboxController operJoy =
      new CommandXboxController(OperatorConstants.OPERATOR_PORT);
  private final Hopper Hopper = new Hopper();

  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.IntakeConstants.kDriverControllerPort);

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
    m_driverController.a().onTrue(Hopper.runIndexer()).onFalse(Hopper.stopIndexer());
   
    m_driverController.a().whileTrue(Hopper.reverseIndexer());

    m_driverController.x().onTrue(Hopper.runHopperMotor()).onFalse(Hopper.stopHopperMotor());
   
    m_driverController.x().whileTrue(Hopper.reverseHopperMotor());
  }
}  
