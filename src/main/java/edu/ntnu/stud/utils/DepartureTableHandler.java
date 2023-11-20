package edu.ntnu.stud.utils;

import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import java.util.Comparator;

/**
 * Class for handling the departure table.
 */
public class DepartureTableHandler {
  /**
   * Method for updating changes in the departure table.
   *
   * @param table The departure table to be updated.
   */
  public static void updateDepartureTable(DepartureTable table) {
    removeDepartedTrains(table);
    sortDepartureTable(table);
  }

  private static void sortDepartureTable(DepartureTable table) {
    Comparator<TrainDeparture> byDepartureTime =
        Comparator.comparing(TrainDeparture::getAdjustedTime);

    table.getDepartureList().sort(byDepartureTime);
  }

  private static void removeDepartedTrains(DepartureTable table) {
    table.getDepartureList().removeIf(
        departure -> departure.getAdjustedTime().isBefore(table.getCurrentTime()));
  }

  public static void addDeparture(DepartureTable table, TrainDeparture departure) {
    table.getDepartureList().add(departure);
  }

  public static void removeDeparture(DepartureTable table, int index) {
    table.getDepartureList().remove(index);
  }
}
