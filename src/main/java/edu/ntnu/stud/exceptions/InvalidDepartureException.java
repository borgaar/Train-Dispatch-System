package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when no input is given.
 */
public class InvalidDepartureException extends Exception {
  public InvalidDepartureException(String message) {
    super(message);
  }
}
