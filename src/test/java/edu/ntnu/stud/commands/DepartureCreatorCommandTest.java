package edu.ntnu.stud.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.input.GetInputFromStream;
import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.NoInputException;
import java.io.ByteArrayInputStream;
import java.time.LocalTime;
import java.util.OptionalInt;
import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
class DepartureCreatorCommandTest {

  InputHandler inputHandler;
  DepartureCreatorCommand departureCreator;
  DepartureTable table;

  @BeforeEach
  void setUp() {
    table = new DepartureTable(LocalTime.of(0, 0));
    inputHandler = new InputHandler(new GetInputFromStream(new Scanner(System.in)));
    departureCreator = new DepartureCreatorCommand(inputHandler);
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(12, 0), "A1", 1234, "Oslo", LocalTime.of(0, 31), OptionalInt.of(3)));
  }

  void simulateInput(String input) {
    System.setIn(new ByteArrayInputStream((input + "\r\n").getBytes()));
  }

  @Test
  void create_Abort() {
    simulateInput("\r");
    var e = assertThrows(NoInputException.class, () -> DepartureCreatorCommand.create(table));

    assertEquals("Input was empty. Aborting.", e.getMessage());
  }
}