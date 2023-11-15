package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.*;

import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.Halt;
import java.time.LocalTime;
import java.util.OptionalInt;

/**
 * Class for adding a departure.
 */
public class CreateDeparture {
  /**
   * Method for creating a departure.
   *
   * @param table the departure table.
   */
  public static void create(DepartureTable table) {
    try {
      final LocalTime delay = getDelay();
      final LocalTime time = getTime(table, delay);
      final String line = getLine();
      final int trainId = getTrainId(table);
      final String destination = getDestination();
      final OptionalInt track = getTrack();

      DepartureTableHandler.addDeparture(table, new TrainDeparture(
          time, line, trainId, destination, delay, track));
    } catch (Exception e) {
      Halt.abortOperation();
    }
  }

  private static LocalTime getDelay() {
    String timeString = InputHandler.getInput(
        "Enter the delay of the departure, "
            + "or leave blank to abort departure creation",
        "HH:MM",
        REGEX_24HR_FORMAT,
        true);

    if (timeString.isEmpty()) {
      throw new IllegalArgumentException("Time cannot be empty. Aborting.");
    }

    return LocalTime.parse(timeString);
  }

  private static LocalTime getTime(DepartureTable table, LocalTime delay) {
    String timeString = InputHandler.getInput(
        "Enter the scheduled time of departure (departure time, without delay), "
            + "or leave blank to abort creation",
        "HH:MM",
        REGEX_24HR_FORMAT,
        true);

    if (timeString.isEmpty()) {
      throw new IllegalArgumentException("Time cannot be empty. Aborting.");
    }

    LocalTime time = LocalTime.parse(timeString);
    LocalTime adjustedTime = time.plusHours(delay.getHour()).plusMinutes(delay.getMinute());

    if (adjustedTime.isBefore(time)) {
      System.out.println("\nThe scheduled departure time, " + time + ", plus the delay, " + delay
          + ", results in a departure time past midnight. Please try again.");
      return getTime(table, delay);

    } else if (adjustedTime.isBefore(table.getCurrentTime())) {
      System.out.println("\nThe scheduled departure time, " + time + ", plus the delay, " + delay
          + ", results in a departure time of " + adjustedTime
          + ", which is before the current time, "
          + table.getCurrentTime() + ". Please try again.");
      return getTime(table, delay);

    }

    return LocalTime.parse(timeString);
  }

  /**
   * Method for getting the line of the departure.
   *
   * @return the line of the departure.
   */
  public static String getLine() {
    String line = InputHandler.getInput(
        "Enter the line of the departure, "
            + "or leave blank to abort departure creation",
        "[ABCD][0-9]",
        REGEX_LINE_FORMAT,
        true);

    if (line.isEmpty()) {
      throw new IllegalArgumentException("Line cannot be empty. Aborting.");
    }

    return line;
  }

  /**
   * Method for getting the train ID of the departure.
   *
   * @param table the departure table.
   * @return the train ID of the departure.
   */
  public static int getTrainId(DepartureTable table) {
    String trainIdString = InputHandler.getInput(
        "Enter the train ID of the departure, "
            + "or leave blank to abort departure creation",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    if (trainIdString.isEmpty()) {
      throw new IllegalArgumentException("Train ID cannot be empty. Aborting.");
    }

    int trainId = Integer.parseInt(trainIdString);

    boolean trainIdExists = table.getDepartureList().stream()
        .anyMatch(departure -> departure.getTrainId() == trainId);

    if (trainIdExists) {
      System.out.println("\nA departure with the train ID " + trainId + " already exists. "
          + "Please try again.");
      return getTrainId(table);
    }

    return trainId;
  }

  /**
   * Method for getting the destination of the departure.
   *
   * @return the destination of the departure.
   */
  public static String getDestination() {
    String destination = InputHandler.getInput(
        "Enter the destination of the departure, "
            + "or leave blank to abort departure creation",
        "[Any name]",
        REGEX_DESTINATION_FORMAT,
        true);

    if (destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be empty. Aborting.");
    }

    return destination;
  }

  /**
   * Method for getting the track of the departure.
   *
   * @return the track of the departure.
   */
  public static OptionalInt getTrack() {
    String trackString = InputHandler.getInput(
        "Enter the track of the departure, "
            + "or leave blank to set no track",
        "[1-5]",
        REGEX_TRACK_FORMAT,
        true);

    if (trackString.isEmpty()) {
      return OptionalInt.empty();
    }

    return OptionalInt.of(Integer.parseInt(trackString));
  }
}