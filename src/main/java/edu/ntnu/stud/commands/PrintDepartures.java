package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Renderer;

/**
 * Class for printing departures.
 */
public class PrintDepartures {
  public static void print(DepartureTable table) {
    Renderer.renderDepartureTable(table);
    Halt.pressEnterToContinue();
  }
}
