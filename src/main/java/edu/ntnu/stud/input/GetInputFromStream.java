package edu.ntnu.stud.input;

import java.util.Scanner;

/**
 * Class for getting input from the user.
 */
public class GetInputFromStream {
  private final Scanner sc;

  public GetInputFromStream(Scanner sc) {
    this.sc = sc;
  }

  public String getString() {
    return sc.nextLine();
  }

}
