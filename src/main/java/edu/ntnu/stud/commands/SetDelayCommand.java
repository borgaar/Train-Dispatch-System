package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_24HR_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.exceptions.InvalidDepartureException;
import edu.ntnu.stud.exceptions.NoDepartureFoundException;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Search;
import java.time.LocalTime;

/**
 * Class for setting/changing the delay of a departure.
 */
public class SetDelayCommand extends Command {

  public SetDelayCommand() {
    super("Set a new delay for a departure");
  }

  /**
   * Sets the delay of a departure.
   *
   * @param table the departure table.
   */
  @Override
  public void run(DepartureTable table)
      throws InvalidDepartureException, NoDepartureFoundException {
    // Get the train ID of the departure to change the delay of
    String trainIdString = inputHandler.getInput(
        "Enter the train ID for the departure you want to change the delay of, "
            + "or leave blank to abort",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    // If the input is empty, abort
    if (trainIdString.isEmpty()) {
      Halt.pressEnterToContinue("Input was empty. Aborting.");
      return;
    }

    // Get the index of the departure to change the delay of
    int trainId = Integer.parseInt(trainIdString);
    int index = Search.getIndexOfTrainId(table, trainId);

    // Get the new delay from the user
    String delayString = inputHandler.getInput(
        "Enter the new delay for the departure "
            + "(blank for no delay). Current delay is "
            + table.getDepartureAt(index).getDelay(),
        "[HH:MM]",
        REGEX_24HR_FORMAT,
        true);

    // If the input is empty, set the delay to none
    LocalTime delay;
    if (delayString.isEmpty()) {
      delay = LocalTime.of(0, 0);

      // If the input is not empty, parse the input
    } else {
      delay = LocalTime.parse(delayString);
    }

    inputHandler.isAdjustedTimeValid(table, delay, table.getDepartureAt(index).getScheduledTime());

    // If the delay is set to none, print a message saying so, otherwise print the new delay
    if (delayString.isEmpty()) {
      Halt.pressEnterToContinue("The delay for departure " + trainId + " has been set to none.");
    } else {
      Halt.pressEnterToContinue("The delay for departure " + trainId + " changed to " + delay);
    }

    // Set the delay of the departure
    table.getDepartureAt(index).setDelay(delay);
  }
}
