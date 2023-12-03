package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_24HR_FORMAT;

import edu.ntnu.stud.exceptions.InvalidTimeException;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import java.time.LocalTime;

/**
 * Class for updating the clock of the system.
 */
public class UpdateClockCommand extends Command {

  public UpdateClockCommand() {
    super("Update the clock of the system");
  }

  /**
   * Updates the clock of the system.
   */
  @Override
  public void run(DepartureTable table) throws InvalidTimeException {
    // Get the new time from the user
    String input = inputHandler.getInput("Enter new time, or leave blank to abort",
        "HH:MM",
        REGEX_24HR_FORMAT,
        true);

    // If the input is empty, abort
    if (input.isEmpty()) {
      Halt.pressEnterToContinue("\nThe time has not been changed.");

      // If the input is not empty, parse it and check if it is valid
    } else {
      LocalTime newTime = LocalTime.parse(input);

      if (newTime.isBefore(table.getCurrentTime()) || newTime.equals(table.getCurrentTime())) {
        throw new InvalidTimeException("The new time (" + newTime
            + ") is before or equal to the current time (" + table.getCurrentTime() + ")");

      } else {
        table.setCurrentTime(newTime);
        Halt.pressEnterToContinue("The time has been changed to " + newTime);
      }
    }
  }
}
