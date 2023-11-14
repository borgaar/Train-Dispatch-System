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
    System.out.println("Updating departure table...");

    removeDepartedTrains(table);
    sortDepartureTable(table);
  }

  private static void sortDepartureTable(DepartureTable table) {
    System.out.println("Sorting departure table...");

    Comparator<TrainDeparture> byDepartureTime =
        Comparator.comparing(TrainDeparture::getAdjustedTime);

    table.getDepartureList().sort(byDepartureTime);
  }

  private static void removeDepartedTrains(DepartureTable table) {
    System.out.println("Removing departed trains...");

    table.getDepartureList().removeIf(
        departure -> departure.getAdjustedTime().isBefore(table.getCurrentTime()));
  }
}
