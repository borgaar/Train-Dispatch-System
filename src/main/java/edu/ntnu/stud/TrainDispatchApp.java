package edu.ntnu.stud;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.commands.DepartureCreatorCommand;
import edu.ntnu.stud.commands.ExitApplicationCommand;
import edu.ntnu.stud.commands.PrintDeparturesCommand;
import edu.ntnu.stud.commands.RemoveDepartureCommand;
import edu.ntnu.stud.commands.SearchDepartureCommand;
import edu.ntnu.stud.commands.SetDelayCommand;
import edu.ntnu.stud.commands.SetTrackCommand;
import edu.ntnu.stud.commands.UpdateClockCommand;
import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Renderer;
import java.time.LocalTime;


/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  private static DepartureTable table;
  private static InputHandler inputHandler = new InputHandler();
  private static final Command[] commands = {
      new PrintDeparturesCommand(),
      new UpdateClockCommand(inputHandler),
      new DepartureCreatorCommand(inputHandler, new java.util.Random()),
      new RemoveDepartureCommand(inputHandler),
      new SetTrackCommand(inputHandler),
      new SetDelayCommand(inputHandler),
      new SearchDepartureCommand(inputHandler),
      new ExitApplicationCommand()};

  public static void main(String[] args) {
    init();
    start();
  }

  private static void init() {
    System.out.println("Initializing...");

    table = new DepartureTable(LocalTime.of(12, 10));
    inputHandler = new InputHandler();

    // Creates a few pre-generated train departures
    TrainDeparture[] testData = {
        new TrainDeparture(
            LocalTime.of(0, 33), "C7", 1435, "Bergen", LocalTime.of(1, 23), 1),
        new TrainDeparture(
            LocalTime.of(16, 15), "C3", 8264, "Trondheim", LocalTime.of(0, 53), 5),
        new TrainDeparture(
            LocalTime.of(8, 58), "A1", 1375, "Oslo", LocalTime.of(0, 0), 3),
        new TrainDeparture(
            LocalTime.of(15, 25), "A1", 9641, "Oslo", LocalTime.of(0, 0), -1),
        new TrainDeparture(
            LocalTime.of(18, 56), "A1", 3477, "Oslo", LocalTime.of(0, 23), 2),
        new TrainDeparture(
            LocalTime.of(12, 10), "B5", 1523, "Kristiansand", LocalTime.of(0, 0),
            2),
        new TrainDeparture(
            LocalTime.of(23, 30), "B8", 9785, "Ålesund", LocalTime.of(0, 0), 1),
        new TrainDeparture(
            LocalTime.of(0, 30), "C6", 2365, "Selbu", LocalTime.of(0, 27), 3),
        new TrainDeparture(
            LocalTime.of(1, 15), "C7", 1735, "Bergen", LocalTime.of(0, 0), 2),
        new TrainDeparture(
            LocalTime.of(10, 25), "C3", 5824, "Trondheim", LocalTime.of(0, 17), 5),
        new TrainDeparture(
            LocalTime.of(3, 10), "D4", 1863, "Stavanger", LocalTime.of(0, 0), 4),
        new TrainDeparture(
            LocalTime.of(14, 15), "C4", 9364, "Norges teknisk-naturvitenskapelige universitet",
            LocalTime.of(0, 0), -1),
        new TrainDeparture(
            LocalTime.of(22, 55), "C3", 3747, "Trondheim", LocalTime.of(0, 0), -1),
        new TrainDeparture(
            LocalTime.of(10, 6), "B2", 1528, "Kragerø", LocalTime.of(0, 0), -1)
    };

    for (TrainDeparture departure : testData) {
      DepartureTableHandler.addDeparture(table, departure);
    }
  }

  private static void start() {
    System.out.println("Starting...");

    int choice;

    while (true) {

      DepartureTableHandler.updateDepartureTable(table);

      Renderer.renderMenu(commands);

      String regexAndPrompt = "[1-" + (commands.length + 1) + "]";
      choice = inputHandler.getInput(
          "Enter an option",
          regexAndPrompt,
          regexAndPrompt,
          false).charAt(0) - '0';

      try {
        commands[choice - 1].run(table);
      } catch (Exception e) {
        Halt.pressEnterToContinue("An error occurred: " + e.getMessage());
      }
    }
  }
}
