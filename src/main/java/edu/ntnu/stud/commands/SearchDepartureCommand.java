package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_ANYTHING;

import edu.ntnu.stud.input.InputHandler;
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

  @Override
  public void run(DepartureTable table) {
    String searchQuery = inputHandler.getInput(
        "Enter the destination or Train ID of the departure you want to search up, "
            + "or leave blank to abort",
        "[1000-9999] / [a-z]",
        REGEX_ANYTHING,
        true);

    if (searchQuery.isEmpty()) {
      System.out.println("\nInput was empty. Aborting.");
      return;
    }

    List<TrainDeparture> matchedDepartures = table.getDepartureList().stream().filter(departure ->
            departure.getDestination().equalsIgnoreCase(searchQuery)
                || departure.getTrainId().toString().equals(searchQuery))
        .toList();

    if (matchedDepartures.isEmpty()) {
      Halt.pressEnterToContinue("No departures with the search query '" + searchQuery
          + "' was found.");
    } else {
      matchedDepartures.forEach(Renderer::renderDetails);
      Halt.pressEnterToContinue();
    }
  }
}
