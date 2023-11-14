package edu.ntnu.stud.input;

/**
 * Class for handling input from the user.
 */
public class InputHandler {

  /**
   * Method for getting input from the user.
   *
   * @param prompt       The prompt to show the user.
   * @param regex        The regex to match the input against.
   * @param blankAllowed Whether or not blank input is allowed.
   * @return The input from the user.
   */
  public static String getInput(String prompt, String regex, boolean blankAllowed) {
    System.out.println(prompt);
    String input = GetInputFromStream.getString();

    while (!input.matches(regex) && !(blankAllowed && input.isEmpty())) {
      System.out.println("Invalid input. Try again.");
      System.out.println(prompt);
      input = GetInputFromStream.getString();
    }

    return input;
  }
}
