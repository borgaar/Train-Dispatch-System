package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_24HR_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.exceptions.InvalidDelayException;
import edu.ntnu.stud.exceptions.NoDepartureFoundException;
import edu.ntnu.stud.input.InputHandler;
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

  @Override
  public void run(DepartureTable table) throws InvalidDelayException, NoDepartureFoundException {
    String trainIdString = inputHandler.getInput(
        "Enter the train ID for the departure you want to change the delay of, "
            + "or leave blank to abort",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    if (trainIdString.isEmpty()) {
      Halt.pressEnterToContinue("Input was empty. Aborting.");
      return;
    }

    int trainId = Integer.parseInt(trainIdString);
    int index = Search.getIndexOfTrainId(table, trainId);

    String delayString = inputHandler.getInput(
        "Enter the new delay for the departure "
            + "(blank for no delay). Current delay is "
            + table.getDepartureAt(index).getDelay(),
        "[HH:MM]",
        REGEX_24HR_FORMAT,
        true);

    LocalTime delay;
    if (delayString.isEmpty()) {
      delay = LocalTime.of(0, 0);
    } else {
      delay = LocalTime.parse(delayString);
    }

    LocalTime newAdjustedTime = table.getDepartureAt(index).getTime()
        .plusHours(delay.getHour())
        .plusMinutes(delay.getMinute());

    if (newAdjustedTime.isBefore(table.getCurrentTime())) {
      throw new InvalidDelayException("The new adjusted departure time ("
          + newAdjustedTime + ") is before the current time (" + table.getCurrentTime() + ")");
    }

    if (delayString.isEmpty()) {
      Halt.pressEnterToContinue("The delay for departure " + trainId + " has been set to none.");
    } else {
      Halt.pressEnterToContinue("The delay for departure " + trainId + " changed to " + delay);
    }

    table.getDepartureAt(index).setDelay(delay);
  }
}
