package edu.ntnu.stud.exceptions;

/**
 * Class for handling exceptions when no departure is found.
 */
public class NoDepartureFoundException extends Exception {
  public NoDepartureFoundException(String message) {
    super(message);
  }
}
