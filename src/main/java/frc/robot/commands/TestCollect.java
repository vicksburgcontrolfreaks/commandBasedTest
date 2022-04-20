// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.TestConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Lifter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TestCollect extends SequentialCommandGroup {
  /** Creates a new TestCollect. */
  public TestCollect(Lifter m_lifter, Collector m_collector) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new LiftDown(m_lifter),
      new CollectorRun(m_collector, TestConstants.collectF),
      new WaitCommand(.3),
      new ParallelDeadlineGroup(
        new WaitCommand(.1), 
        new LiftUp(m_lifter)
        ),
      new LiftUp(m_lifter)
    );
  }
}
