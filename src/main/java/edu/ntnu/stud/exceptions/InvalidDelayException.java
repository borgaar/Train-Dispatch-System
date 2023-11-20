package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when no input is given.
 */
public class InvalidDelayException extends Exception {
  public InvalidDelayException(String message) {
    super(message);
  }
}
