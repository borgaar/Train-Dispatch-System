package edu.ntnu.stud.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import edu.ntnu.stud.input.InputHandler;
import java.util.OptionalInt;

@ExtendWith(MockitoExtension.class)
class CreateDepartureTest {

  @Mock
  private InputHandler mockInputHandler;
  private CreateDeparture departureCreator;

  @BeforeEach
  void setUp() {
    departureCreator = new CreateDeparture(mockInputHandler);
  }

  @Test
  void create() {
  }

  @Test
  void getTrack_emptyInput() {
    when(mockInputHandler.getInput(anyString(), anyString(), anyString(), anyBoolean()))
        .thenReturn("\n");

    OptionalInt expected = OptionalInt.empty();
    OptionalInt actual = departureCreator.getTrack();

    assertEquals(expected, actual);
  }

  @Test
  void getTrack_validInput() {
    when(mockInputHandler.getInput(anyString(), anyString(), anyString(), anyBoolean()))
        .thenReturn("1");

    OptionalInt expected = OptionalInt.of(1);
    OptionalInt actual = departureCreator.getTrack();

    assertEquals(expected, actual);
  }

  @Test
  void getDestination() {
  }

  @Test
  void getDelay() {
  }

  @Test
  void getTime() {
  }

  @Test
  void getLine() {
  }

  @Test
  void getTrainId() {
  }
}