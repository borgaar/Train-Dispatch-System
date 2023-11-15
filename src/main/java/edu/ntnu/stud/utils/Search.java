package edu.ntnu.stud.utils;

import edu.ntnu.stud.models.DepartureTable;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Search {
  public static OptionalInt GetIndexByTrainId(DepartureTable table, int trainId) {
    return IntStream.range(0, table.getDepartureList().size())
        .filter(i -> table.getDepartureList().get(i).getTrainId() == trainId)
        .findFirst();
  }
}
