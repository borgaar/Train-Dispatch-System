package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_24HR_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_DESTINATION_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_LINE_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRACK_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.NoInputException;
import edu.ntnu.stud.utils.Search;
import java.time.LocalTime;
import java.util.OptionalInt;
import java.util.Random;

/**
 * Class for adding a departure.
 */
public class CreateDeparture {

  private final InputHandler inputHandler;

  public CreateDeparture(InputHandler inputHandler) {
    this.inputHandler = inputHandler;
  }

  /**
   * Method for creating a departure.
   *
   * @param table the departure table.
   */
  public void create(DepartureTable table) {
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
      Halt.abortWithMessage("\nNo input detected.");
    }
  }

  /**
   * Method for getting the track of the departure.
   *
   * @return the track of the departure.
   */
  private OptionalInt getTrack() {
    String trackString = inputHandler.getInput(
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

  /**
   * Method for getting the destination of the departure.
   *
   * @return the destination of the departure.
   */
  private String getDestination() throws NoInputException {
    String destination = inputHandler.getInput(
        "Enter the destination of the departure, "
            + "or leave blank to abort departure creation",
        "[Any name]",
        REGEX_DESTINATION_FORMAT,
        true);

    if (destination.isEmpty()) {
      throw new NoInputException("Input was empty.");
    }

    return destination;
  }

  private LocalTime getDelay() throws NoInputException {
    String timeString = inputHandler.getInput(
        "Enter the delay of the departure, "
            + "or leave blank to abort departure creation",
        "HH:MM",
        REGEX_24HR_FORMAT,
        true);

    if (timeString.isEmpty()) {
      throw new NoInputException("No input was detected.");
    }

    return LocalTime.parse(timeString);
  }

  private LocalTime getTime(DepartureTable table, LocalTime delay) throws NoInputException {
    String timeString = inputHandler.getInput(
        "Enter the scheduled time of departure (departure time, without delay), "
            + "or leave blank to abort creation",
        "HH:MM",
        REGEX_24HR_FORMAT,
        true);

    if (timeString.isEmpty()) {
      throw new NoInputException("Input was empty.");
    }

    LocalTime time = LocalTime.parse(timeString);
    LocalTime adjustedTime = time.plusHours(delay.getHour()).plusMinutes(delay.getMinute());

    if (adjustedTime.isBefore(time)) {
      System.out.println("\nThe scheduled departure time, " + time + ", plus the delay, " + delay
          + ", results in a departure time past midnight. Please enter a new time.");
      return getTime(table, delay);

    } else if (adjustedTime.isBefore(table.getCurrentTime())) {
      System.out.println("\nThe scheduled departure time, " + time + ", plus the delay, " + delay
          + ", results in a departure time of " + adjustedTime
          + ", which is before the current time, "
          + table.getCurrentTime() + ". Please enter a new time.");
      return getTime(table, delay);

    }

    return LocalTime.parse(timeString);
  }

  /**
   * Method for getting the line of the departure.
   *
   * @return the line of the departure.
   */
  public String getLine() throws NoInputException {
    String line = inputHandler.getInput(
        "Enter the line of the departure, "
            + "or leave blank to abort departure creation",
        "[ABCD][0-9]",
        REGEX_LINE_FORMAT,
        true);

    if (line.isEmpty()) {
      throw new NoInputException("Input was empty.");
    }

    return line;
  }

  /**
   * Method for getting the train ID of the departure.
   *
   * @param table the departure table.
   * @return the train ID of the departure.
   */
  public int getTrainId(DepartureTable table) {
    String trainIdString = inputHandler.getInput(
        "Enter the train ID of the departure, "
            + "or leave blank to autogenerate a unique ID",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    if (trainIdString.isEmpty()) {
      Random rnd = new Random();

      int trainId;
      do {
        trainId = rnd.nextInt(0, 9000) + 1000;
      } while (Search.getIndexByTrainId(table, trainId).isPresent());

      return trainId;
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
}
