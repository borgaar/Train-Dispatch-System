package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when no input is given.
 */
public class NoDepartureFoundException extends Exception {
  public NoDepartureFoundException(String message) {
    super(message);
  }
}
