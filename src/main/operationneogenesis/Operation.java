//Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
    package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Operation extends TimedRobot {
    // instance variables for motor and controller
    private CANSparkMax neoMotor; // neo Motor via Spark Max
    private Joystick xboxController; // Xbox Controller

    // constants for xbox controller button/axis
    private static final int BUTTON_Y = 4; // Y button

    @Override 
    public void robotIntit() {
        // Spark Max controller on CAN ID 5
        neoMotor = new CANSparkMax(5, MotorType.kBrushless);
        neoMotor.restoreFactoryDefaults();
         // motor to brake mode
        neoMotor.setIdleMode(CANSparkMax.IdleMode.kBrake); 
        

        // Initialize xbox controller (USB port: 0)
        xboxController = new Joystick(0);

        
        // final CommandXboxController xboxController = new CommandXboxController(0); unsure 
    }
    @Override 
    public void teleopPeriodic() {

        // get the X-Axis value of the left stick (range: -1.0 to 1.0)
        boolean isYPressed = xboxController.getRawButton(BUTTON_Y);

        // set motor speed 
         neoMotor.setVoltage(6.0);

         if (isYPressed) {
            neoMotor.set(0.5); // Activate motor at 50% speed
            System.out.println("Motor activated!");
         } else {
            neoMotor.set(0); // Stop motor
         }
         }


    }
