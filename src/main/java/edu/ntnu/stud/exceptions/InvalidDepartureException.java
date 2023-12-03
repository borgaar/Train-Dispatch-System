package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when a departure is invalid.
 */
public class InvalidDepartureException extends Exception {
  public InvalidDepartureException(String message) {
    super(message);
  }
}
