
package frc.robot;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Hopper;



public class RobotContainer {
  
  private final HopperSubsystem  = new HopperSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  public RobotContainer() {
    configureBindings();
  }

private void configureBindings() {
  
m_driverController.a();onTrue(runIndexer()).onFasle(!runIndexer());   

m_driverController.a();whileTrue(reverseIndexer()).onFasle(!reverseIndexer()); 

m_driverController.b();onTrue(stopIndexer()).onFasle(!stopIndexer());
 
m_driverController.x();onTrue(runHopperMotor()).onFasle(!runHopperMotor());

m_driverController.x();whileTrue(reverseHopperMotor() ).onFasle(!CommandreverseHopperMotor());

m_driverController.y();onTrue(stopHopperMotor()).onFasle(!stopHopperMotor());
  }
  }
  