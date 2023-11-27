package edu.ntnu.stud.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;

class DepartureTableTest {

  private final TrainDeparture departure1 = new TrainDeparture(
      LocalTime.of(14, 0),
      "A1",
      1234,
      "Oslo",
      LocalTime.of(0, 15),
      1);
  private final TrainDeparture departure2 = new TrainDeparture(
      LocalTime.of(13, 0),
      "B2",
      4321,
      "Trondheim",
      LocalTime.of(0, 0),
      -1);
  private DepartureTable table;

  @BeforeEach
  public void setUp() {
    table = new DepartureTable(LocalTime.of(12, 30));
    table.addDeparture(departure1);
    table.addDeparture(departure2);
  }

  @Test
  void getDepartureList() {
    ArrayList<TrainDeparture> expected = new ArrayList<>();
    expected.add(departure1);
    expected.add(departure2);

    ArrayList<TrainDeparture> actual = table.getDepartureList();

    assertEquals(expected, actual, "Departure list should be equal");
  }

  @Test
  void getDepartureAt() {
    TrainDeparture actual = table.getDepartureAt(0);

    assertEquals(departure1, actual, "Departures should be equal");
  }

  @Test
  void getCurrentTime() {
    LocalTime expected = LocalTime.of(12, 30);
    LocalTime actual = table.getCurrentTime();

    assertEquals(expected, actual, "Current time should be 12:30");
  }

  @Test
  void setCurrentTime() {
    assertEquals(LocalTime.of(12, 30), table.getCurrentTime(), "Current time should be 12:30 before setting");

    table.setCurrentTime(LocalTime.of(13, 0));

    LocalTime expected = LocalTime.of(13, 0);
    LocalTime actual = table.getCurrentTime();

    assertEquals(expected, actual, "Current time should be 13:00 after setting");
  }

  @Test
  void updateDepartureTable() {
    boolean isSorted = table.getDepartureAt(0).getAdjustedTime()
        .isBefore(table.getDepartureAt(1).getAdjustedTime());

    assertFalse(isSorted, "Departure at index 0 should not be before departure at index 1 before sorting");

    table.updateDepartureTable();

    isSorted = table.getDepartureAt(0).getAdjustedTime()
        .isBefore(table.getDepartureAt(1).getAdjustedTime());

    assertTrue(isSorted, "Departure at index 0 should be before departure at index 1 after sorting");
  }

  @Test
  void addDeparture() {
    TrainDeparture departure3 = new TrainDeparture(
        LocalTime.of(15, 0),
        "C3",
        5678,
        "Bergen",
        LocalTime.of(0, 0),
        2);

    table.addDeparture(departure3);

    TrainDeparture actual = table.getDepartureAt(2);

    assertEquals(departure3, actual, "Departures should be equal");
  }

  @Test
  void removeDeparture() {
    table.removeDeparture(0);

    TrainDeparture actual = table.getDepartureAt(0);

    assertEquals(departure2, actual, "Departures should be equal");
  }
}