package edu.ntnu.stud.input;

import static edu.ntnu.stud.utils.Constants.INVALID_INPUT_MESSAGE;

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

    return input;
  }
}
