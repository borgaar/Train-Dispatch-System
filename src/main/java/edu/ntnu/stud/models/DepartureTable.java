package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * DepartureTable.java Class for creating a departure table and modifying/adding departures to it.
 */
public class DepartureTable {
  // Object variables
  private final ArrayList<TrainDeparture> departureList = new ArrayList<>();
  private LocalTime currentTime;

  public DepartureTable(LocalTime time) {
    currentTime = time;
  }

  public ArrayList<TrainDeparture> getDepartureList() {
    return departureList;
  }

  public LocalTime getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(LocalTime time) {
    currentTime = time;
  }
}
