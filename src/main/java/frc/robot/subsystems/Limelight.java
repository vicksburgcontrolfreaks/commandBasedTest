// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Limelight extends SubsystemBase {
    /** Creates a new Limelight. */
  NetworkTableInstance inst;
  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry tv;

  public Limelight (){
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");

  }

  @Override
  public void periodic() {
    //periodically checks the degrees off of the target in the x and y directions, the percent of the image that the target takes up, and whether or not a target is visible.
    tx();
    ty();
    ta();
    tv();
    fancyDistance();
    inRange();
    SmartDashboard.putNumber("Fancy Distance", fancyDistance());
    SmartDashboard.putNumber("Area", ta());
    SmartDashboard.putBoolean("Visible?", tv());
    SmartDashboard.putNumber("fromX", tx());
    SmartDashboard.putBoolean("inRange?", inRange());
  }

  public double tx(){
    //returns the number of degrees off of the target in the x direction
    return tx.getDouble(0.0);
  }

  public double ty(){
    //returns the number of degrees off of the target in the y direction
    return ty.getDouble(0.0);
  }

  public double ta(){
    //returns the percent of the limelights view that the area of the target takes up
    return ta.getDouble(0.0);
  }

  public boolean tv(){
    //returns a boolean based on whether a target is visible or not
    if(tv.getDouble(0.0) == 1)
      return true;
    else 
      return false;
  }

  public double fancyDistance(){
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = 45.0;

    // distance from the center of the Limelight lens to the floor
    double limelightHeightInches = 21.75;

    // distance from the target to the floor
    double goalHeightInches = 102.625;

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightHeightInches)/Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }

  public boolean inRange(){
    if(fancyDistance() >= ShooterConstants.lowRange && fancyDistance() <= ShooterConstants.highRange)
      return true;
    else
      return false;
  }

}