package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_24HR_FORMAT;

import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import java.time.LocalTime;

/**
 * Class for updating the clock of the system.
 */
public class UpdateClock {

  private static InputHandler inputHandler;

  public UpdateClock(InputHandler inputHandler) {
    UpdateClock.inputHandler = inputHandler;
  }

  /**
   * Updates the clock of the system.
   */
  public static void updateClock(DepartureTable table) {
    String input = inputHandler.getInput("Enter new time, or leave blank to abort",
        "HH:MM",
        REGEX_24HR_FORMAT,
        true);

    if (input.isEmpty()) {
      Halt.abortWithMessage("\nThe time has not been changed.");

    } else {
      LocalTime newTime = LocalTime.parse(input);

      if (newTime.isBefore(table.getCurrentTime()) || newTime.equals(table.getCurrentTime())) {
        System.out.println("\nThe new time is before or equal to the current time, "
            + "which is an invalid time. Please try again.");
        updateClock(table);

      } else {
        table.setCurrentTime(newTime);
        System.out.println("\nThe time has been changed to " + newTime);
        Halt.pressEnterToContinue();
      }
    }
  }
}
