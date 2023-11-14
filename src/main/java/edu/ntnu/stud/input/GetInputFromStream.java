package edu.ntnu.stud.input;

/**
 * Class for getting input from the user.
 */
public class GetInputFromStream {
  private static final java.util.Scanner sc = new java.util.Scanner(System.in);

  public static String getString() {
    return sc.nextLine();
  }
}
