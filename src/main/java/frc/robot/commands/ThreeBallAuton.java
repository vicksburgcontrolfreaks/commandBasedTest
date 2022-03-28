// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class ThreeBallAuton extends SequentialCommandGroup {
  /** Creates a new Autonomous1. This is the autonomous made for a starting position nearest to the side wall. */
  public ThreeBallAuton(MechTrain m_drive, Collector m_collector, Shooter m_shooter, Indexer m_indexer, MrMills m_mills, Turret m_turret, Limelight m_limelight) {
    //adds each stage of our autonomous to a sequential group
    addCommands(
      //drives the robot forward while running the collector. Both shut off when the distance has been driven.
      new ParallelDeadlineGroup(
        new DriveDistance(m_drive, 45, .5), 
        new CollectorRun(m_collector, TestConstants.collectF)),
      new TurnDegrees(m_drive, 172, DriveConstants.drive_kMaxOutput),
      new AutoTurret(m_turret, m_limelight, true),
      //turns on the shooter and fires 2 cargo into the Upper Hub
      new PrimingSequence(m_collector, m_indexer, m_mills, m_shooter, m_limelight),
      // new LoadCheck(m_indexer, m_shooter, m_mills),
      new IndexCheck(m_indexer, m_collector, m_mills),
      // new LoadCheck(m_indexer, m_shooter, m_mills),
      new TurnDegrees(m_drive, 156, .25),
      new DriveDistance(m_drive, 164, .5),
      new TurnDegrees(m_drive, 32, .25),

      new DriveDistance(m_drive, -110, .5),
      new TurnDegrees(m_drive, 154, .25),
      new AutoTurret(m_turret, m_limelight, true),
      //turns on the shooter and fires 2 cargo into the Upper Hub
      new PrimingSequence(m_collector, m_indexer, m_mills, m_shooter, m_limelight)
      // new LoadCheck(m_indexer, m_shooter, m_mills)
      
    );
  }
}