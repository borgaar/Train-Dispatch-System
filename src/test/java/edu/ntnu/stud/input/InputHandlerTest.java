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
  private String toSingleInputString(String[] inputs) {
    StringBuilder sb = new StringBuilder();
    for (String input : inputs) {
      sb.append(input).append("\n");
    }
    return sb.toString();
  }

  // Test if the input handler returns the correct input when given valid user input
  @Test
  void testValidInput() {
    String input = "a\n";

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    InputHandler inputHandler = new InputHandler();

    String expected = "a";
    String result = inputHandler.getInput("prompt", "format", "[a-z]", false);

    assertEquals(expected, result);
  }

  // Test if the input handler returns the correct input when given invalid user input first, then valid user input
  @Test
  void testInvalidInput() {
    String[] listOfInputs = {"3", "a"};

    String input = toSingleInputString(listOfInputs);

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    InputHandler inputHandler = new InputHandler();

    String expected = "a";
    String result = inputHandler.getInput("prompt", "format", "[a-z]", false);

    assertEquals(expected, result);
    assertTrue(outContent.toString().contains(INVALID_INPUT_MESSAGE));
  }

  // Test if the input handler returns the correct input when given blank user input first (when it's not allowed), then valid user input
  @Test
  void testBlankInputNotAllowed() {
    String[] listOfInputs = {"", "a"};

    String input = toSingleInputString(listOfInputs);

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    InputHandler inputHandler = new InputHandler();

    String expected = "a";
    String result = inputHandler.getInput("prompt", "format", "[a-z]", false);

    assertEquals(expected, result);
    assertTrue(outContent.toString().contains(INVALID_INPUT_MESSAGE));
  }

  @Test
  void testBlankInputAllowed() {
    String[] listOfInputs = {"", "a"};

    String input = toSingleInputString(listOfInputs);

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    InputHandler inputHandler = new InputHandler();

    String expected = "";
    String result = inputHandler.getInput("prompt", "format", "[a-z]", true);

    assertEquals(expected, result);
    assertFalse(outContent.toString().contains(INVALID_INPUT_MESSAGE));
  }
}
