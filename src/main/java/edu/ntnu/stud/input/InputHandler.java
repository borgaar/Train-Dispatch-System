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
  public static String getInput(String prompt, String format, String regex, boolean blankAllowed) {
    String fullPrompt = prompt + " --> " + format + " --> ";
    System.out.print(fullPrompt);
    String input = GetInputFromStream.getString();

    if (!input.matches(regex) && !(blankAllowed && input.isEmpty())) {
      System.out.println("Invalid input. Try again.");
      System.out.print(fullPrompt);
      input = GetInputFromStream.getString();
    }

    return input;
  }
}
