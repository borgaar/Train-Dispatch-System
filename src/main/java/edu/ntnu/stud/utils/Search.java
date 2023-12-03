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

    // Get the index of the departure
    OptionalInt index = getIndex(table, trainId);

    // Throw an exception if the index is empty
    if (index.isEmpty()) {
      throw new NoDepartureFoundException("No departure with the train ID " + trainId + " exists.");
    }

    // Return the index
    return index.getAsInt();
  }


  /**
   * Method for getting the index of a departure by train ID.
   *
   * @param table   the departure table.
   * @param trainId the train ID.
   * @return the index of the departure.
   */
  private static OptionalInt getIndex(DepartureTable table, int trainId) {
    // 1. Stream an integer range with same size as table (stream of indices)
    // 2. Filter the stream by train ID matching the train ID of the departure at the index 'i'
    // 3. Get the first matching index
    return IntStream.range(0, table.getDepartureList().size())
        .filter(i -> table.getDepartureAt(i).getTrainId() == trainId)
        .findFirst();
  }
}
