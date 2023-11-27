package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

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

  public TrainDeparture getDepartureAt(int index) {
    return departureList.get(index);
  }

  public LocalTime getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(LocalTime time) {
    currentTime = time;
  }

  public void updateDepartureTable() {
    removeDepartedTrains();
    sortDepartureTable();
  }

  private void removeDepartedTrains() {
    departureList.removeIf(departure -> departure.getAdjustedTime().isBefore(currentTime));
  }

  private void sortDepartureTable() {
    departureList.sort(Comparator.comparing(TrainDeparture::getAdjustedTime));
  }

  public void addDeparture(TrainDeparture departure) {
    departureList.add(departure);
  }

  public void removeDeparture(int index) {
    departureList.remove(index);
  }
}
