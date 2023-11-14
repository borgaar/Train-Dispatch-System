package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * DepartureTable.java Class for creating a departure table and modifying/adding departures to it.
 */
public class DepartureTable {
  // Object variables
  private final ArrayList<TrainDeparture> departureList = new ArrayList<>();
  private final LocalTime currentTime = LocalTime.of(0, 0);

  public ArrayList<TrainDeparture> getDepartureList() {
    return departureList;
  }

  public LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Method for adding a departure to the Departure Table.
   *
   * @param departure The departure to be added.
   */
  public void addDeparture(TrainDeparture departure) {
    departureList.add(departure);
  }
}
