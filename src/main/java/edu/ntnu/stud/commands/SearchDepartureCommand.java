package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_ANYTHING;

import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Renderer;
import java.util.List;

/**
 * Class for searching for the details of a specific departure.
 */
public class SearchDepartureCommand extends Command {

  public SearchDepartureCommand() {
    super("Search for departure details");
  }

  /**
   * Method for searching for the details of a specific departure.
   */
  @Override
  public void run(DepartureTable table) {
    // Get the search query from the user
    String searchQuery = inputHandler.getInput(
        "Enter the destination or Train ID of the departure you want to search up, "
            + "or leave blank to abort",
        "[1000-9999] / [a-z]",
        REGEX_ANYTHING,
        true);

    // If the input is empty, abort
    if (searchQuery.isEmpty()) {
      System.out.println("\nInput was empty. Aborting.");
      return;
    }

    // Get a list of all departures that match the search query
    List<TrainDeparture> matchedDepartures = table.getDepartureList().stream().filter(departure ->
            departure.getDestination().equalsIgnoreCase(searchQuery)
                || departure.getTrainId().toString().equals(searchQuery))
        .toList();


    // If no departures matched the search query, print a 'none found'-message and abort
    if (matchedDepartures.isEmpty()) {
      Halt.pressEnterToContinue("No departures with the search query '" + searchQuery
          + "' was found.");

      // If one or more departures matched the search query, print the details of each departure
    } else {
      matchedDepartures.forEach(Renderer::renderDetails);
      Halt.pressEnterToContinue();
    }
  }
}
