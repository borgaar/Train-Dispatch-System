package edu.ntnu.stud.input;

/**
 * Class for handling input from the user.
 */
public class InputHandler {
  private final GetInputFromStream inputFromStream;

  public InputHandler(GetInputFromStream inputFromStream) {
    this.inputFromStream = inputFromStream;
  }

  /**
   * Method for getting input from the user.
   *
   * @param prompt       The prompt to show the user.
   * @param regex        The regex to match the input against.
   * @param blankAllowed Whether or not blank input is allowed.
   * @return The input from the user.
   */
  public String getInput(
      String prompt,
      String format,
      String regex,
      boolean blankAllowed) {

    String fullPrompt = prompt + " --> " + format + " --> ";
    System.out.print(fullPrompt);
    String input = inputFromStream.getString();

    if (!input.matches(regex) && !(blankAllowed && input.isEmpty())) {
      System.out.println("Invalid input. Try again.");
      System.out.print(fullPrompt);
      input = inputFromStream.getString();
    }

    return input;
  }
}
