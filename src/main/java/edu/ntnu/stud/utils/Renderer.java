package edu.ntnu.stud.utils;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import java.time.LocalTime;

/**
 * Class for rendering different elements.
 */
public class Renderer {

  /**
   * Renders the main menu options.
   */
  public static void renderMenu(Command[] commands) {
    System.out.print("""
                
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                         MAIN MENU                         ┃
        ┣━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
        ┃ Num ┃ Action to be performed                              ┃
        ┠╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┨
        """);

    for (int i = 0; i < commands.length; i++) {
      System.out.print(formatToTableEntry(
          Integer.toString(i + 1),
          3,
          false,
          false));
      System.out.print(formatToTableEntry(commands[i].getName(),
          51,
          true,
          true));
    }

    System.out.println("┃  " + (commands.length + 1)
        + "  ┃ Exit the application                                ┃");
    System.out.println("""
        ┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
  }

  /**
   * Method for rendering the departure table.
   *
   * @param table The departure table to be rendered.
   */
  public static void renderDepartureTable(DepartureTable table) {

    printHeaders(table);

    table.getDepartureList().forEach(departure -> {
      System.out.print(formatToTableEntry(departure.getAdjustedTime().toString(),
          5,
          false,
          false));
      System.out.print(formatToTableEntry(departure.getLine(),
          4,
          false,
          false));
      System.out.print(formatToTableEntry(departure.getTrainId().toString(),
          8,
          false,
          false));
      System.out.print(formatToTableEntry(departure.getDestination().toUpperCase(),
          15,
          false,
          true));
      System.out.print(formatToTableEntry(
          departure.getDelay().equals(LocalTime.of(0, 0))
              ? " "
              : departure.getDelay().toString(),
          5,
          false,
          false));
      System.out.print(formatToTableEntry(
          departure.getTrack().isPresent()
              ? Integer.toString(departure.getTrack().getAsInt())
              : " ",
          5,
          true,
          false));
    });

    if (!table.getDepartureList().isEmpty()) {
      // Print bottom border
      System.out.println("┗━━━━━━━┻━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━┻━━━━━━━┻━━━━━━━┛");
    }
  }


  private static void printHeaders(DepartureTable table) {

    // Print borders and current time window
    System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
    System.out.println("┃                   CURRENT TIME - " + table.getCurrentTime()
        + "                    ┃");

    // Print borders and "NO DEPARTURES"-window if there are no departures
    if (table.getDepartureList().isEmpty()) {
      System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
      System.out.println("┃                    NO DEPARTURES FOUND                    ┃");
      System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

      // Print borders and departure table headers if there are departures
    } else {
      System.out.println("┣━━━━━━━┳━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┳━━━━━━━┳━━━━━━━┫");
      System.out.println("┃ Time  ┃ Line ┃ Train ID ┃ Destination     ┃ Delay ┃ Track ┃");
      System.out.println("┠╌╌╌╌╌╌╌╂╌╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╂╌╌╌╌╌╌╌╂╌╌╌╌╌╌╌┨");
    }
  }

  /**
   * Method for rendering the details of a train departure.
   *
   * @param trainDeparture The train departure to be rendered.
   */
  public static void renderDetails(TrainDeparture trainDeparture) {
    System.out.print("""
                
        ┏━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓
        ┃    Details    ┃       Values       ┃
        ┠╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┨
        """);

    System.out.print(formatToTableEntry(
        "Time w/ delay",
        13,
        false,
        true));
    System.out.print(formatToTableEntry(
        trainDeparture.getAdjustedTime().toString(),
        18,
        true,
        true));

    System.out.print(formatToTableEntry(
        "Line",
        13,
        false,
        true));
    System.out.print(formatToTableEntry(
        trainDeparture.getLine(),
        18,
        true,
        true));

    System.out.print(formatToTableEntry(
        "Train ID",
        13,
        false,
        true));
    System.out.print(formatToTableEntry(
        trainDeparture.getTrainId().toString(),
        18,
        true,
        true));

    System.out.print(formatToTableEntry(
        "Destination",
        13,
        false,
        true));
    System.out.print(formatToTableEntry(
        trainDeparture.getDestination().toUpperCase(),
        18,
        true,
        true));

    System.out.print(formatToTableEntry(
        "Delay",
        13,
        false,
        true));
    System.out.print(formatToTableEntry(
        trainDeparture.getDelay().equals(LocalTime.of(0, 0))
            ? " "
            : trainDeparture.getDelay().toString(),
        18,
        true,
        true));

    System.out.print(formatToTableEntry(
        "Track",
        13,
        false,
        true));
    System.out.print(formatToTableEntry(
        trainDeparture.getTrack().isPresent()
            ? Integer.toString(trainDeparture.getTrack().getAsInt())
            : " ",
        18,
        true,
        true));

    System.out.println("┗━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
  }

  private static String formatToLength(String entry, int targetLength, boolean leftAlign) {
    if (entry.length() > targetLength) {
      return entry.substring(0, targetLength - 2) + "..";

    } else if (leftAlign) {
      return entry + " ".repeat(targetLength - entry.length());

    } else {
      int totalPadding = targetLength - entry.length();
      return " ".repeat(totalPadding / 2) + entry + " ".repeat(totalPadding / 2);
    }
  }

  /**
   * Method for formatting a string to a given length and adding a border around it.
   *
   * @param entry        The entry to format
   * @param targetLength The length to format the entry to
   * @param lastColumn   Whether the entry is in the last column in a table
   * @param leftAlign    Whether to left align the entry or not
   * @return The formatted entry
   */
  private static String formatToTableEntry(String entry,
                                           int targetLength,
                                           boolean lastColumn,
                                           boolean leftAlign) {

    String formattedEntry = formatToLength(entry, targetLength, leftAlign);
    formattedEntry = formattedEntry.length() < targetLength ? formattedEntry + " " : formattedEntry;
    formattedEntry = lastColumn ? formattedEntry + " ┃\n" : formattedEntry + " ";

    return "┃ " + formattedEntry;
  }
}
