package edu.ntnu.stud.utils;

import edu.ntnu.stud.exceptions.NoDepartureFoundException;
import edu.ntnu.stud.models.DepartureTable;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Class for searching the departure table.
 */
public class Search {

  /**
   * Method for getting the index of a departure by train ID.
   *
   * @param table the departure table.
   * @return the index of the departure.
   */
  public static int getIndexOfTrainId(DepartureTable table, int trainId)
      throws NoDepartureFoundException {

    OptionalInt index = getIndex(table, trainId);

    if (index.isEmpty()) {
      throw new NoDepartureFoundException("No departure with the train ID " + trainId + " exists.");
    }

    return index.getAsInt();
  }

  private static OptionalInt getIndex(DepartureTable table, int trainId) {
    return IntStream.range(0, table.getDepartureList().size())
        .filter(i -> table.getDepartureAt(i).getTrainId() == trainId)
        .findFirst();
  }
}
