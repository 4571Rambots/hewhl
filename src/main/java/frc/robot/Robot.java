//Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
// import com.revrobotics.spark.SparkBase.ResetMode;
// import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
// import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;

public class Robot extends TimedRobot {
    // instance variables for motor and controller
    private SparkMax neoMotor; // neo Motor via Spark Max
    private RelativeEncoder neoEncoder;
    private Joystick xboxController; 

    // constants for xbox controller button/axis

    @Override 
    public void robotInit() {
        System.out.println("Initializing robot...");
        
        try {
            // Spark Max controller on CAN ID 5
            neoMotor = new SparkMax(5, MotorType.kBrushless);
            neoEncoder = neoMotor.getEncoder();
            
            // Create and apply configuration
            SparkMaxConfig config = new SparkMaxConfig();
            config.idleMode(IdleMode.kBrake);
            
            // Configure and persist settings
            // REVLibError error = neoMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
            
            // Print initialization info
            // System.out.println("=== Motor Initialization ===");
            // System.out.println("Configuration result: " + error);
            // System.out.println("Device ID: " + neoMotor.getDeviceId());
            // System.out.println("Firmware version: " + neoMotor.getFirmwareString());
            // System.out.println("Bus voltage: " + neoMotor.getBusVoltage());
            // System.out.println("Has faults: " + neoMotor.hasStickyFault());
            // System.out.println("Encoder: " + neoEncoder.toString());
            xboxController = new Joystick(0);

            if (neoMotor.hasStickyFault()) {
                System.out.println("Sticky faults: " + neoMotor.getStickyFaults());
                neoMotor.clearFaults(); // Try to clear any faults
            }
            
            neoEncoder.setPosition(0);
        } catch (Exception e) {
            System.out.println("ERROR during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override 
    public void teleopPeriodic() {
        try {
            boolean isYPressed = xboxController.getRawButton(4);

            neoMotor.set(isYPressed ? 0.02 : 0);
            
            // Print detailed diagnostics
            // System.out.println("=== Motor Status ===");
            // System.out.println("Command sent: " + neoMotor.get());
            // System.out.println("Applied output: " + neoMotor.getAppliedOutput());
            // System.out.println("Bus voltage: " + neoMotor.getBusVoltage());
            // System.out.println("Output current: " + neoMotor.getOutputCurrent());
            // System.out.println("Temperature: " + neoMotor.getMotorTemperature());
            
            // Check for active faults
            if (neoMotor.hasActiveFault()) {
                System.out.println("ACTIVE FAULTS DETECTED:");
                System.out.println(neoMotor.getFaults());
            }
            
        } catch (Exception e) {
            System.out.println("ERROR during teleop: " + e.getMessage());
        }
    }
}
