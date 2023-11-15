package edu.ntnu.stud.utils;

import edu.ntnu.stud.models.DepartureTable;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Class for searching the departure table.
 */
public class Search {
  /**
   * Method for getting the index of a train by its ID.
   *
   * @param table   the departure table.
   * @param trainId the train ID.
   * @return the index of the train.
   */
  public static OptionalInt getIndexByTrainId(DepartureTable table, int trainId) {
    return IntStream.range(0, table.getDepartureList().size())
        .filter(i -> table.getDepartureList().get(i).getTrainId() == trainId)
        .findFirst();
  }
}
