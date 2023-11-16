package edu.ntnu.stud.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InputHandlerTest {

  @Mock
  private GetInputFromStream mockInputFromStream;
  private InputHandler inputHandler;

  @BeforeEach
  void setUp() {
    inputHandler = new InputHandler(mockInputFromStream);
  }

  @Test
  void getInput() {
  }
}