package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import java.time.LocalTime;

/**
 * Class for updating the clock of the system.
 */
public class UpdateClock {
  /**
   * Updates the clock of the system.
   */
  public static void updateClock(DepartureTable table) {
    String input = InputHandler.getInput("Enter new time, or leave blank to abort: [HH:MM]",
        "([01]?[0-9]|2[0-3]):[0-5][0-9]",
        true);

    if (input.isEmpty()) {
      System.out.println("Aborting. The time has not been changed.");
      Halt.pressEnterToContinue();

    } else {
      int hour = Integer.parseInt(input.substring(0, 2));
      int minute = Integer.parseInt(input.substring(3, 5));

      LocalTime newTime = LocalTime.of(hour, minute);

      if (newTime.isBefore(table.getCurrentTime()) || newTime.equals(table.getCurrentTime())) {
        System.out.println("The new time is before or equal to the current time, "
            + "which is an invalid time. Please try again.");
        updateClock(table);

      } else {
        table.setCurrentTime(newTime);
        System.out.println("The time has been changed to " + newTime);
        Halt.pressEnterToContinue();
      }
    }
  }
}
