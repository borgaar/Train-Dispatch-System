package edu.ntnu.stud.utils;

import java.util.Scanner;

public class Halt {
  public static void pressEnterToContinue() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Press enter to continue...");
    sc.nextLine();
  }
}
