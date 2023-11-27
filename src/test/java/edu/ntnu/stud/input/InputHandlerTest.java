package edu.ntnu.stud.input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static edu.ntnu.stud.utils.Constants.INVALID_INPUT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class InputHandlerTest {

  // Setup for checking output to console
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(originalOut);
  }

  // Helper method for converting an array of inputs to a single string simulating user input
  private void toInputStream(String[] userInputs) {
    StringBuilder sb = new StringBuilder(); // Create a string builder

    // Append each input to the string builder, separated by '\r\n' being the user pressing enter
    for (String input : userInputs) {
      sb.append(input).append("\r\n"); // '\r\n' simulates the user pressing enter
    }

    String input = sb.toString(); // Convert the string builder to a string

    // Set the input stream to the string
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  }

  // Test if the input handler returns the correct input when given valid user input
  @Test
  void testValidInput() {
    String[] input = {"a"}; // The user input to be simulated

    toInputStream(input); // Set the input stream to the list of inputs

    InputHandler inputHandler = new InputHandler(); // Create an input handler

    String expected = "a";  // The expected result is 'a'
    String result = inputHandler.getInput("prompt", "format", "[a-z]", false);  // The format is [a-z], so 'a' is valid

    assertEquals(expected, result); // Check if the result is as expected
  }

  // Test if the input handler returns the correct input when given invalid user input first, then valid user input
  @Test
  void testInvalidInput() {
    String[] userInput = {"3", "a"};  // Simulates the user typing '3', then 'a'

    toInputStream(userInput); // Set the input stream to the list of inputs

    InputHandler inputHandler = new InputHandler(); // Create an input handler

    String expected = "a";  // The expected result is 'a'
    String result = inputHandler.getInput("prompt", "format", "[a-z]", false);  // The format is [a-z], so '3' is invalid

    assertEquals(expected, result); // Check if the result is as expected
    assertTrue(outContent.toString().contains(INVALID_INPUT_MESSAGE));  // Should print invalid input message
  }

  // Test if the input handler returns the correct input when given blank user input first (when it's not allowed), then valid user input
  @Test
  void testBlankInputNotAllowed() {
    String[] listOfInputs = {"", "a"};  // Simulates the user pressing enter without typing anything (blank input),
                                        // followed by the user typing 'a'
    toInputStream(listOfInputs);   // Set the input stream to the list of inputs

    InputHandler inputHandler = new InputHandler(); // Create an input handler

    String expected = "a";  // The expected result is 'a'
    String result = inputHandler.getInput("prompt", "format", "[a-z]", false);  // Blank input is not allowed

    assertEquals(expected, result); // Check if the result is as expected
    assertTrue(outContent.toString().contains(INVALID_INPUT_MESSAGE));  // Should print invalid input message
  }

  @Test
  void testBlankInputAllowed() {
    String[] listOfInputs = {"", "a"};  // Simulates the user pressing enter without typing anything (blank input),
                                        // followed by the user typing 'a'. Since blank input is allowed, the
                                        // invalid input message should not be printed as the first input is valid.

    toInputStream(listOfInputs);     // Set the input stream to the list of inputs

    InputHandler inputHandler = new InputHandler(); // Create an input handler

    String expected = ""; // The expected result is blank input
    String result = inputHandler.getInput("prompt", "format", "[a-z]", true); // Blank input is allowed

    assertEquals(expected, result); // Check if the result is as expected
    assertFalse(outContent.toString().contains(INVALID_INPUT_MESSAGE)); // Should not print invalid input message
  }                                                                     // as blank input is allowed
}
