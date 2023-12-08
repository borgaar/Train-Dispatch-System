package edu.ntnu.stud.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

class TrainDepartureTest {

  private TrainDeparture departure;

  @BeforeEach
  public void setUp() {
    departure = new TrainDeparture(
        LocalTime.of(12, 30),
        "A1",
        1234,
        "Oslo",
        LocalTime.of(0, 15),
        1);
  }

  @Test
  void getScheduledTime() {
    LocalTime expected = LocalTime.of(12, 30);
    LocalTime actual = departure.getScheduledTime();

    assertEquals(expected, actual, "Scheduled time should be 12:30");
  }

  @Test
  void getTrack() {
    int expected = 1;
    int actual = departure.getTrack();

    assertEquals(expected, actual, "Track should be 1");
  }

  @Test
  void setTrack() {
    int expected = 2;
    departure.setTrack(2);
    int actual = departure.getTrack();

    assertEquals(expected, actual, "Track should be 2");
  }

  @Test
  void getLine() {
    String expected = "A1";
    String actual = departure.getLine();

    assertEquals(expected, actual, "Line should be A1");
  }

  @Test
  void getTrainId() {
    int expected = 1234;
    int actual = departure.getTrainId();

    assertEquals(expected, actual, "Train ID should be 1234");
  }

  @Test
  void getDestination() {
    String expected = "Oslo";
    String actual = departure.getDestination();

    assertEquals(expected, actual, "Destination should be Oslo");
  }

  @Test
  void getDelay() {
    LocalTime expected = LocalTime.of(0, 15);
    LocalTime actual = departure.getDelay();

    assertEquals(expected, actual, "Delay should be 00:15");
  }

  @Test
  void setDelay() {
    LocalTime expected = LocalTime.of(0, 30);
    departure.setDelay(LocalTime.of(0, 30));
    LocalTime actual = departure.getDelay();

    assertEquals(expected, actual, "Delay should be 00:30");
  }

  @Test
  void getAdjustedTime() {
    LocalTime expected = LocalTime.of(12, 45);
    LocalTime actual = departure.getAdjustedTime();

    assertEquals(expected, actual, "Adjusted time should be 12:45");
  }
}