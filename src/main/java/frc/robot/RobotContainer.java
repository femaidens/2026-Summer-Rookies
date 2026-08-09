
package frc.robot;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Hopper;
import frc.robot.Constants.OperatorConstants;


public class RobotContainer {
   private final Hopper hopper;

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort),
  hopper = new Hopper();

  public RobotContainer() {configureBindings();
  }

private void configureBindings() {
  
m_driverController.a();onTrue(hopper.runIndexer()).onFasle(hopper.stopIndexer());

m_driverController.a();whileTrue(hopper.reverseIndexer());
 
m_driverController.x();onTrue(hopper.runHopperMotor()).onFasle(hopper.stopHopperMotor());

m_driverController.x();whileTrue(hopper.reverseHopperMotor());

  }
  }
  