package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.DepartureTable;

/**
 * Command class for exiting the application.
 */
public class ExitApplicationCommand extends Command {

  /**
   * Constructor for the ExitApplicationCommand class.
   */
  public ExitApplicationCommand() {
    super("Exit the application");
  }

  @Override
  public void run(DepartureTable table) {
    System.out.println("Exiting application...");
    System.exit(0);
  }
}
