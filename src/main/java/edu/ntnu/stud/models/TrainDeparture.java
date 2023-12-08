package edu.ntnu.stud.models;

import java.time.LocalTime;

/**
 * TrainDeparture class for the program.
 */
public class TrainDeparture {
  private final LocalTime scheduledTime;
  private final String line;
  private final Integer trainId;
  private final String destination;
  private LocalTime delay;
  private int track;

  /**
   * Constructor for the TrainDeparture record.
   *
   * @param scheduledTime the scheduled time of departure.
   * @param line          the line of the train.
   * @param trainId       the train identification.
   * @param destination   the destination of the train.
   * @param delay         the delay of the train.
   * @param track         the track of the train.
   */
  // Constructor
  public TrainDeparture(
      LocalTime scheduledTime,
      String line,
      Integer trainId,
      String destination,
      LocalTime delay,
      int track) {
    this.scheduledTime = scheduledTime;
    this.line = line;
    this.trainId = trainId;
    this.destination = destination;
    this.delay = delay;
    this.track = track;
  }

  public LocalTime getScheduledTime() {
    return scheduledTime;
  }

  public int getTrack() {
    return track;
  }

  public void setTrack(int track) {
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
    return scheduledTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }
}
