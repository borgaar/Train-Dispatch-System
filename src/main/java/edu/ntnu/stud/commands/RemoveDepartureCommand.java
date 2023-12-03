package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.exceptions.NoDepartureFoundException;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Search;

/**
 * Class for removing a departure.
 */
public class RemoveDepartureCommand extends Command {

  public RemoveDepartureCommand() {
    super("Remove a departure from the departure table");
  }

  /**
   * Method for removing a departure.
   *
   * @param table the departure table.
   */
  @Override
  public void run(DepartureTable table) throws NoDepartureFoundException {
    // Get the train ID of the departure to remove from the table
    String trainIdString = inputHandler.getInput(
        "Enter the train ID for the departure you want to remove, or leave blank to abort",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    // If the input is empty, abort
    if (trainIdString.isEmpty()) {
      System.out.println("\nInput was empty. Aborting.");
      return;
    }

    int trainId = Integer.parseInt(trainIdString);

    int removeIndex = Search.getIndexOfTrainId(table, trainId);

    // Remove the departure from the table.
    table.removeDeparture(removeIndex);
    Halt.pressEnterToContinue("Departure with the train ID " + trainId + " has been removed.");
  }
}
