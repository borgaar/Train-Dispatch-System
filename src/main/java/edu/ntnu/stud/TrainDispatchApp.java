package edu.ntnu.stud;

import edu.ntnu.stud.commands.CreateDeparture;
import edu.ntnu.stud.commands.PrintDepartures;
import edu.ntnu.stud.commands.RemoveDeparture;
import edu.ntnu.stud.commands.UpdateClock;
import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.Renderer;
import java.time.LocalTime;
import java.util.OptionalInt;


/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  private static DepartureTable table;

  public static void main(String[] args) {
    init();
    start();
  }

  private static void init() {
    System.out.println("Initializing...");

    table = new DepartureTable(LocalTime.of(12, 10));

    // Creates a few pre-generated train departures
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(0, 33), "C7", 1435, "Bergen", LocalTime.of(1, 23), OptionalInt.of(1)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(16, 15), "C3", 8264, "Trondheim", LocalTime.of(0, 53), OptionalInt.of(5)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(8, 58), "A1", 1375, "Oslo", LocalTime.of(0, 0), OptionalInt.of(3)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(15, 25), "A1", 9641, "Oslo", LocalTime.of(0, 0), OptionalInt.empty()));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(18, 56), "A1", 3477, "Oslo", LocalTime.of(0, 23), OptionalInt.of(2)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(12, 10), "B5", 1523, "Kristiansand", LocalTime.of(0, 0),
        OptionalInt.of(2)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(23, 30), "B8", 9785, "Ålesund", LocalTime.of(0, 0), OptionalInt.of(1)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(0, 30), "C6", 2365, "Selbu", LocalTime.of(0, 27), OptionalInt.of(3)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(1, 15), "C7", 1735, "Bergen", LocalTime.of(0, 0), OptionalInt.of(2)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(10, 25), "C3", 5824, "Trondheim", LocalTime.of(0, 17), OptionalInt.of(5)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(3, 10), "D4", 1863, "Stavanger", LocalTime.of(0, 0), OptionalInt.of(4)));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(14, 15), "C4", 9364, "Norges teknisk-naturvitenskapelige universitet",
        LocalTime.of(0, 0), OptionalInt.empty()));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(22, 55), "C3", 3747, "Trondheim", LocalTime.of(0, 0),
        OptionalInt.empty()));
    DepartureTableHandler.addDeparture(table, new TrainDeparture(
        LocalTime.of(10, 6), "B2", 1528, "Kragerø", LocalTime.of(0, 0), OptionalInt.empty()));
  }

  private static void start() {
    System.out.println("Starting...");

    int choice;

    while (true) {

      DepartureTableHandler.updateDepartureTable(table);

      Renderer.renderMenu();

      choice = InputHandler.getInput(
          "Enter an option",
          "[1-8]",
          "[1-8]",
          false).charAt(0) - '0';

      switch (choice) {
        case 1 -> PrintDepartures.print(table);
        case 2 -> UpdateClock.updateClock(table);
        case 3 -> CreateDeparture.create(table);
        case 4 -> RemoveDeparture.removeDepartureByTrainId(table);
        case 5 -> System.out.println("Not yet implemented");
        case 6 -> System.out.println("Not yet implemented");
        case 7 -> System.out.println("Not yet implemented");
        case 8 -> {
          System.out.println("Exiting...");
          System.exit(0);
        }
        default -> System.out.println("Invalid input. Try again.");
      }
    }
  }
}
