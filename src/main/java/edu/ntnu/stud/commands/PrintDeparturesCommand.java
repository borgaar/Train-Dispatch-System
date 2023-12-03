package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Renderer;

/**
 * Class for printing departures.
 */
public class PrintDeparturesCommand extends Command {

  public PrintDeparturesCommand() {
    super("Print all scheduled departures");
  }

  /**
   * Prints all departures as a departure table.
   */
  @Override
  public void run(DepartureTable table) {
    Renderer.renderDepartureTable(table);
    Halt.pressEnterToContinue();
  }
}
