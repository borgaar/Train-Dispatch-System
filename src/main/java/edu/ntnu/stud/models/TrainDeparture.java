package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.Objects;
import java.util.OptionalInt;

/**
 * TrainDeparture class for the program.
 */
public class TrainDeparture {
  private final LocalTime time;
  private final String line;
  private final Integer trainId;
  private final String destination;
  private LocalTime delay;
  private OptionalInt track;

  /**
   * Constructor for the TrainDeparture record.
   *
   * @param time        the time of departure.
   * @param line        the line of the train.
   * @param trainId     the train identification.
   * @param destination the destination of the train.
   * @param delay       the delay of the train.
   * @param track       the track of the train.
   */
  // Constructor
  public TrainDeparture(
      LocalTime time, String line, Integer trainId,
      String destination, LocalTime delay, OptionalInt track) {
    this.time = time;
    this.line = line;
    this.trainId = trainId;
    this.destination = destination;
    this.delay = delay;
    this.track = track;
  }

  public LocalTime getTime() {
    return time;
  }

  public OptionalInt getTrack() {
    return track;
  }

  public void setTrack(OptionalInt track) {
    this.track = track;
  }

  public String getLine() {
    return line;
  }

  public Integer getTrainId() {
    return trainId;
  }

  public String getDestination() {
    return destination;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  public LocalTime getAdjustedTime() {
    return time.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, line, trainId, destination, delay, track);
  }
}
