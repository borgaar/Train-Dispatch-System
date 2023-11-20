package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when no input is given.
 */
public class InvalidTimeException extends Exception {
  public InvalidTimeException(String message) {
    super(message);
  }
}
