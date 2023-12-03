package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when the current time is invalid.
 */
public class InvalidTimeException extends Exception {
  public InvalidTimeException(String message) {
    super(message);
  }
}
