// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FireCheck extends SequentialCommandGroup {
  /** Creates a new FireCheck. This fires two cargo while the shooter is at full speed.*/
  /*public FireCheck() {
    Indexer m_indexer = new Indexer();
    Shooter m_shooter = new Shooter();
    MrMills m_mills = new MrMills();
    Collector m_collector = new Collector();
    //adds each stage of the firing process, running sequentially. This loads a cargo if one is indexed, then indexes and fires a second cargo
    addCommands(new LoadCheck(m_indexer, m_shooter, m_mills),
    new IndexCheck(m_indexer, m_collector, m_mills), 
    new LoadCheck(m_indexer, m_shooter, m_mills));
  }
}*/