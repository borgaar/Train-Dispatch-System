package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_24HR_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_DESTINATION_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_LINE_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRACK_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_YN_FORMAT;

import edu.ntnu.stud.exceptions.InvalidDepartureException;
import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.DepartureTableHandler;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Renderer;
import java.time.LocalTime;
import java.util.OptionalInt;
import java.util.Random;

/**
 * Class for adding a departure.
 */
public class DepartureCreatorCommand extends Command {

  private final InputHandler inputHandler;
  private final Random random;

  /**
   * Constructor for the DepartureCreatorCommand class.
   *
   * @param inputHandler the input handler.
   * @param random       the random number generator.
   */
  public DepartureCreatorCommand(InputHandler inputHandler, Random random) {
    super("Add a departure to the departure table");
    this.inputHandler = inputHandler;
    this.random = random;
  }

  @Override
  public void run(DepartureTable table) throws InvalidDepartureException {
    String delayString = inputHandler.getInput(
        "Enter the delay of the departure",
        "[HH:MM]",
        REGEX_24HR_FORMAT,
        false);
    String timeString = inputHandler.getInput(
        "Enter the time of the departure",
        "[HH:MM]",
        REGEX_24HR_FORMAT,
        false);

    LocalTime delay = LocalTime.parse(delayString);
    LocalTime time = LocalTime.parse(timeString);

    isTimeValid(table, delay, time);

    String line = inputHandler.getInput(
        "Enter the line of the departure",
        "[A-D][1-9]",
        REGEX_LINE_FORMAT,
        false);

    int trainId = getTrainId(table);

    String destination = inputHandler.getInput(
        "Enter the destination of the departure",
        "[a-Z]",
        REGEX_DESTINATION_FORMAT,
        false);

    OptionalInt track = getTrack();

    TrainDeparture departure = new TrainDeparture(time, line, trainId, destination, delay, track);

    verifyDetails(table, departure);
  }

  private void isTimeValid(
      DepartureTable table,
      LocalTime delay,
      LocalTime time) throws InvalidDepartureException {

    if (time.isAfter(time.plusHours(delay.getHour()).plusMinutes(delay.getMinute()))) {
      throw new InvalidDepartureException("The scheduled departure time ("
          + time + ") plus the delay ("
          + delay + ") is past midnight.");

    } else if (time.plusHours(delay.getHour()).plusMinutes(delay.getMinute())
        .isBefore(table.getCurrentTime())) {
      throw new InvalidDepartureException("The scheduled departure time ("
          + time + ") plus the delay ("
          + delay + ") is before the current time ("
          + table.getCurrentTime() + ").");
    }
  }

  private int autogenerateTrainId(DepartureTable table) {
    int trainId;

    do {
      trainId = 1000 + random.nextInt(9000);
    } while (!isIdUnique(table, trainId));

    System.out.println("Autogenerated train ID: " + trainId);

    return trainId;
  }

  private boolean isIdUnique(DepartureTable table, int trainId) {
    return table.getDepartureList().stream()
        .filter(departure -> departure.getTrainId() == trainId).findAny().isEmpty();
  }

  private int getTrainId(DepartureTable table) throws InvalidDepartureException {
    String trainIdString = inputHandler.getInput(
        "Enter a unique train ID for the departure, or leave blank to autogenerate",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    if (trainIdString.isEmpty()) {
      return autogenerateTrainId(table);

    } else if (isIdUnique(table, Integer.parseInt(trainIdString))) {
      return Integer.parseInt(trainIdString);

    } else {
      throw new InvalidDepartureException("Train ID " + trainIdString + " is already in use.");
    }
  }

  private OptionalInt getTrack() {
    String trackString = inputHandler.getInput(
        "Enter the track of the departure, or leave blank if unknown",
        "[1-5]",
        REGEX_TRACK_FORMAT,
        true);

    if (trackString.isEmpty()) {
      return OptionalInt.empty();
    } else {
      return OptionalInt.of(Integer.parseInt(trackString));
    }
  }

  private void verifyDetails(DepartureTable table, TrainDeparture departure) {
    Renderer.renderDetails(departure);
    String isCorrectAnswer = inputHandler.getInput(
        "Is this correct?",
        "[Y/n]",
        REGEX_YN_FORMAT,
        true);

    if (isCorrectAnswer.isEmpty() || isCorrectAnswer.equalsIgnoreCase("Y")) {
      DepartureTableHandler.addDeparture(
          table,
          departure);
      Halt.pressEnterToContinue("The departure has been added.");

    } else if (isCorrectAnswer.equalsIgnoreCase("N")) {
      Halt.pressEnterToContinue("The departure will not be added. Aborting.");
    }
  }
}
