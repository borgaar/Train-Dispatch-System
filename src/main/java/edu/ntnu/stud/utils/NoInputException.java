package edu.ntnu.stud.utils;

/**
 * Class for handling exceptions when no input is given.
 */
public class NoInputException extends Exception {
  public NoInputException(String message) {
    super(message);
  }
}
