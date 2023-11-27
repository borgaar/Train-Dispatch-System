package edu.ntnu.stud.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

class TrainDepartureTest {

  private final TrainDeparture departure = new TrainDeparture(
      LocalTime.of(12, 30),
      "A1",
      1234,
      "Oslo",
      LocalTime.of(0, 15),
      1);

  @Test
  void getTime() {
    LocalTime expected = LocalTime.of(12, 30);
    LocalTime actual = departure.getTime();

    assertEquals(expected, actual);
  }

  @Test
  void getTrack() {
    int expected = 1;
    int actual = departure.getTrack();

    assertEquals(expected, actual);
  }

  @Test
  void setTrack() {
    int expected = 2;
    departure.setTrack(expected);
    int actual = departure.getTrack();

    assertEquals(expected, actual);

    expected = -1;
    departure.setTrack(expected);
    actual = departure.getTrack();

    assertEquals(expected, actual);
  }

  @Test
  void getLine() {
    String expected = "A1";
    String actual = departure.getLine();

    assertEquals(expected, actual);
  }

  @Test
  void getTrainId() {
    int expected = 1234;
    int actual = departure.getTrainId();

    assertEquals(expected, actual);
  }

  @Test
  void getDestination() {
    String expected = "Oslo";
    String actual = departure.getDestination();

    assertEquals(expected, actual);
  }

  @Test
  void getDelay() {
    LocalTime expected = LocalTime.of(0, 15);
    LocalTime actual = departure.getDelay();

    assertEquals(expected, actual);
  }

  @Test
  void setDelay() {
    LocalTime expected = LocalTime.of(0, 30);
    departure.setDelay(expected);
    LocalTime actual = departure.getDelay();

    assertEquals(expected, actual);
  }

  @Test
  void getAdjustedTime() {
    LocalTime expected = LocalTime.of(12, 45);
    LocalTime actual = departure.getAdjustedTime();

    assertEquals(expected, actual);
  }
}