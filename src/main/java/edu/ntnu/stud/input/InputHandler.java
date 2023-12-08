package edu.ntnu.stud.input;

import static edu.ntnu.stud.utils.Constants.INVALID_INPUT_MESSAGE;

import edu.ntnu.stud.exceptions.InvalidDepartureException;
import edu.ntnu.stud.models.DepartureTable;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Class for handling input from the user.
 */
public class InputHandler {
  private final Scanner sc;

  public InputHandler() {
    sc = new Scanner(System.in);
  }

  /**
   * Method for getting input from the user.
   *
   * @param prompt       The prompt to show the user.
   * @param regex        The regex to match the input against.
   * @param blankAllowed Whether blank input is allowed.
   * @return The input from the user.
   */
  public String getInput(
      String prompt,
      String format,
      String regex,
      boolean blankAllowed) {

    String input;

    // Loop until the input is valid
    boolean inputIsInvalid;
    do {
      inputIsInvalid = false;
      String fullPrompt = prompt + " --> " + format + " --> ";
      System.out.print(fullPrompt);

      input = sc.nextLine();

      if (!input.matches(regex) && !(blankAllowed && input.isEmpty())) {
        inputIsInvalid = true;
        System.out.println(INVALID_INPUT_MESSAGE);
      }
    } while (inputIsInvalid);

    // Return the input
    return input;
  }


  /**
   * Method for checking if the time is valid.
   *
   * @param table         the departure table.
   * @param delay         the delay of the departure.
   * @param scheduledTime the scheduled (planned) time of the departure.
   * @throws InvalidDepartureException if the time is past midnight or before current time.
   */
  public void isAdjustedTimeValid(
      DepartureTable table,
      LocalTime delay,
      LocalTime scheduledTime) throws InvalidDepartureException {

    LocalTime newAdjustedTime = scheduledTime
        .plusHours(delay.getHour())
        .plusMinutes(delay.getMinute());

    // Throw an exception if the scheduled time is past midnight or before current time
    if (scheduledTime.isAfter(newAdjustedTime)) {
      throw new InvalidDepartureException("The scheduled departure time ("
          + scheduledTime + ") plus the delay ("
          + delay + "), results in the adjusted departure time of "
          + newAdjustedTime + " the next day, which is past midnight.");

      // Throw an exception if the time is before current time
    } else if (newAdjustedTime.isBefore(table.getCurrentTime())) {
      throw new InvalidDepartureException("The scheduled departure time ("
          + scheduledTime + ") plus the delay ("
          + delay + "), results in the adjusted departure time of "
          + newAdjustedTime + " which is before the current time ("
          + table.getCurrentTime() + ").");
    }
  }
}
