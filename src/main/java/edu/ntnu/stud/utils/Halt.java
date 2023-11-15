package edu.ntnu.stud.utils;

import java.util.Scanner;

/**
 * Class for halting the program.
 */
public class Halt {
  /**
   * Halts the program until the user presses enter.
   */
  public static void pressEnterToContinue() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\nPress enter to continue...");
    sc.nextLine();
  }

  public static void abortOperation() {
    System.out.print("Aborting. ");
    pressEnterToContinue();
  }

  public static void abortWithMessage(String message) {
    System.out.print(message + " ");
    abortOperation();
  }
}
