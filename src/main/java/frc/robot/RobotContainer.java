
package frc.robot;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Hopper;
import frc.robot.HopperConstants;


public class RobotContainer {
  private final Hopper Hopper = new Hopper();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().onTrue(Hopper.runIndexer()).onFalse(Hopper.stopIndexer());
   
    m_driverController.a().whileTrue(Hopper.reverseIndexer());

    m_driverController.x().onTrue(Hopper.runHopperMotor()).onFalse(Hopper.stopHopperMotor());
   
    m_driverController.x().whileTrue(Hopper.reverseHopperMotor());
  }
}  