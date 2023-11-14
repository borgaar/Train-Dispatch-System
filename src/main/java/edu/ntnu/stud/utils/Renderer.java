package edu.ntnu.stud.utils;

import edu.ntnu.stud.models.DepartureTable;
import java.time.LocalTime;

/**
 * Class for rendering different elements.
 */
public class Renderer {

  /**
   * Renders the main menu options.
   */
  public static void renderMenu() {
    System.out.println("""
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                         MAIN MENU                         ┃
        ┣━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
        ┃ Num ┃ Action to be performed                              ┃
        ┠╌╌╌╌╌╂╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┨
        ┃  1  ┃ Print departure table                               ┃
        ┃  2  ┃ Update the current time                             ┃
        ┃  3  ┃ Add a departure to table                            ┃
        ┃  4  ┃ Remove a departure from table                       ┃
        ┃  5  ┃ Set a track for a departure                         ┃
        ┃  6  ┃ Set a new delay for a departure                     ┃
        ┃  7  ┃ Search for a train by Train ID or destination       ┃
        ┃  8  ┃ Exit the program                                    ┃
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
      System.out.print(TextMutilation.formatToTableEntry(departure.getAdjustedTime().toString(),
          5,
          false,
          false));
      System.out.print(TextMutilation.formatToTableEntry(departure.getLine(),
          4,
          false,
          false));
      System.out.print(TextMutilation.formatToTableEntry(departure.getTrainId().toString(),
          8,
          false,
          false));
      System.out.print(TextMutilation.formatToTableEntry(departure.getDestination().toUpperCase(),
          15,
          false,
          true));
      System.out.print(TextMutilation.formatToTableEntry(
          departure.getDelay().equals(LocalTime.of(0, 0))
              ? " "
              : departure.getDelay().toString(),
          5,
          false,
          false));
      System.out.println(TextMutilation.formatToTableEntry(
          departure.getTrack().isPresent()
              ? Integer.toString(departure.getTrack().getAsInt())
              : " ",
          5,
          true,
          false));
    });

    // Print bottom border
    System.out.println("┗━━━━━━━┻━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━┻━━━━━━━┻━━━━━━━┛");
  }


  private static void printHeaders(DepartureTable table) {

    // Print borders and current time window
    System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
    System.out.println("┃                  CURRENT TIME - " + table.getCurrentTime()
        + "                     ┃");

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

}
