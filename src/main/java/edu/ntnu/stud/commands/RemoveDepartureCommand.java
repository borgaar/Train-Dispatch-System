package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Search;
import java.util.OptionalInt;

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
  public void run(DepartureTable table) {
    String trainIdString = inputHandler.getInput(
        "Enter the train ID for the departure you want to remove, or leave blank to abort",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    if (trainIdString.isEmpty()) {
      Halt.abortOperation();
      return;
    }

    int trainId = Integer.parseInt(trainIdString);

    OptionalInt removeIndex = Search.getIndexByTrainId(table, trainId);

    if (removeIndex.isPresent()) {
      DepartureTableHandler.removeDeparture(table, removeIndex.getAsInt());
      System.out.println("\nDeparture with the train ID " + trainId + " has been removed.");
      Halt.pressEnterToContinue();
    } else {
      System.out.println("\nNo departure with the train ID " + trainId
          + " exists. Please try again.");
      removeDepartureByTrainId(table);
    }
  }
}
