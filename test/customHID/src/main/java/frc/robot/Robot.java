package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj.GenericHID;

public class Robot extends TimedRobot {
  GenericHID hid = new GenericHID(0);

  SparkMax LMotor = new SparkMax(6, MotorType.kBrushless);
  SparkMax RMotor = new SparkMax(5, MotorType.kBrushless);

  double setpoint;

  public Robot() {
    SparkMaxConfig LMotorCfg = new SparkMaxConfig();
    LMotorCfg
      .inverted(true)
      .idleMode(IdleMode.kBrake);
    LMotorCfg.encoder
      .positionConversionFactor(11.43/15.0);
    LMotorCfg.closedLoop
      .pid(0.1, 0, 0)
      .outputRange(-1.0, 1.0)
      .positionWrappingInputRange(0, 75.0);

    SparkMaxConfig RMotorCfg = new SparkMaxConfig();
    RMotorCfg
      .inverted(false)
      .idleMode(IdleMode.kBrake);
    RMotorCfg.encoder
      .positionConversionFactor(11.43/15.0);
    RMotorCfg.closedLoop
      .pid(0.1, 0, 0)
      .outputRange(-1.0, 1.0)
      .positionWrappingInputRange(0, 75.0);

    LMotor.configure(LMotorCfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    RMotor.configure(RMotorCfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    setpoint = 0;
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void teleopInit() {
    LMotor.set(0);
    RMotor.set(0);
  }

  @Override
  public void teleopPeriodic() {
    boolean w = hid.getRawButton(1);
    boolean t = hid.getRawButton(2);
    boolean f = hid.getRawButton(3);

    if(w) {
      setpoint = 0.0;
    }
    else if(t) {
      setpoint = 10.0;
    }
    else if(f) {
      setpoint = 20.0;
    }

    LMotor.getClosedLoopController().setReference(setpoint, ControlType.kPosition);
    RMotor.getClosedLoopController().setReference(setpoint, ControlType.kPosition);
  }
}
