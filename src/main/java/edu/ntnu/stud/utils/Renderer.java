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
    // Print top headers
    System.out.print("""
                
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                         MAIN MENU                         ┃
        ┣━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
        ┃ Num ┃ Action to be performed                              ┃
        ┠╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┨
        """);

    // Print menu options
    for (int i = 0; i < commands.length; i++) {
      printAsTableEntry(
          Integer.toString(i + 1),
          3,
          false,
          false);
      printAsTableEntry(commands[i].getName(),
          51,
          true,
          true);
    }

    // Print bottom border
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

    // Print the departures
    table.getDepartureList().forEach(departure -> {
      printAsTableEntry(departure.getAdjustedTime().toString(),
          5,
          false,
          false);
      printAsTableEntry(departure.getLine(),
          4,
          false,
          false);
      printAsTableEntry(departure.getTrainId().toString(),
          8,
          false,
          false);
      printAsTableEntry(departure.getDestination().toUpperCase(),
          15,
          false,
          true);
      printAsTableEntry(
          departure.getDelay().equals(LocalTime.of(0, 0))
              ? " "
              : departure.getDelay().toString(),
          5,
          false,
          false);
      printAsTableEntry(
          departure.getTrack() == -1
              ? " "
              : Integer.toString(departure.getTrack()),
          5,
          true,
          false);
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
    // Print top header
    System.out.print("""
                
        ┏━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓
        ┃    Details    ┃       Values       ┃
        ┠╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┨
        """);

    // Print details
    printAsTableEntry(
        "Time w/ delay",
        13,
        false,
        true);
    printAsTableEntry(
        trainDeparture.getAdjustedTime().toString(),
        18,
        true,
        true);

    printAsTableEntry(
        "Line",
        13,
        false,
        true);
    printAsTableEntry(
        trainDeparture.getLine(),
        18,
        true,
        true);

    printAsTableEntry(
        "Train ID",
        13,
        false,
        true);
    printAsTableEntry(
        trainDeparture.getTrainId().toString(),
        18,
        true,
        true);

    printAsTableEntry(
        "Destination",
        13,
        false,
        true);
    printAsTableEntry(
        trainDeparture.getDestination().toUpperCase(),
        18,
        true,
        true);

    printAsTableEntry(
        "Delay",
        13,
        false,
        true);
    printAsTableEntry(
        trainDeparture.getDelay().equals(LocalTime.of(0, 0))
            ? "No delay"
            : trainDeparture.getDelay().toString(),
        18,
        true,
        true);

    printAsTableEntry(
        "Track",
        13,
        false,
        true);
    printAsTableEntry(
        trainDeparture.getTrack() != -1
            ? Integer.toString(trainDeparture.getTrack())
            : "None assigned",
        18,
        true,
        true);

    // Print bottom border
    System.out.println("┗━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
  }

  /**
   * Method for formatting a string to a given length,
   * and whether to center the string between whitespaces.
   *
   * @param entry        The entry to format
   * @param targetLength The length to format the entry to
   * @param leftAlign    Whether to left align the entry or not
   */

  private static String formatToLength(String entry, int targetLength, boolean leftAlign) {
    // If the entry is longer than the target length, shorten it and add '..'
    if (entry.length() > targetLength) {
      return entry.substring(0, targetLength - 2) + "..";

      // If the entry is shorter than the target length and leftAlign, add whitespaces at the end
    } else if (leftAlign) {
      return entry + " ".repeat(targetLength - entry.length());

      // If the entry is shorter than the target length and not leftAlign, add whitespaces around it
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
   */
  private static void printAsTableEntry(String entry,
                                        int targetLength,
                                        boolean lastColumn,
                                        boolean leftAlign) {

    // Make the entry the correct length
    String formattedEntry = formatToLength(entry, targetLength, leftAlign);

    // Add one whitespace at the end if the entry is shorter than the target length
    formattedEntry = formattedEntry.length() < targetLength ? formattedEntry + " " : formattedEntry;

    // Add a border at the end if the entry is in the last column
    formattedEntry = lastColumn ? formattedEntry + " ┃\n" : formattedEntry + " ";

    // Print the entry
    System.out.print("┃ " + formattedEntry);
  }
}
