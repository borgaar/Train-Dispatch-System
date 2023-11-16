package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;

/**
 * Class for adding a departure.
 */
public class DepartureCreatorCommand extends Command {

  public DepartureCreatorCommand() {
    super("Add a departure to the departure table");
  }

  @Override
  public void run(DepartureTable table) {
    System.out.println("Not yet implemented");
    Halt.pressEnterToContinue();
  }
}
