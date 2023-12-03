package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_TRACK_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.exceptions.NoDepartureFoundException;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Search;

/**
 * Class for setting/changing the track of a departure.
 */
public class SetTrackCommand extends Command {

  public SetTrackCommand() {
    super("Change the track of a departure");
  }


  /**
   * Sets the track of a departure.
   *
   * @param table the departure table.
   */
  @Override
  public void run(DepartureTable table) throws NoDepartureFoundException {
    String trainIdString = inputHandler.getInput(
        "Enter the train ID for the departure you want to change the track of, "
            + "or leave blank to abort",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    // If the input is empty, abort
    if (trainIdString.isEmpty()) {
      Halt.pressEnterToContinue("Input was empty. Aborting.");
      return;
    }

    // Get the index of the departure to change the delay of
    int trainId = Integer.parseInt(trainIdString);
    int index = Search.getIndexOfTrainId(table, trainId);

    // Get the new delay from the user
    String trackString = inputHandler.getInput(
        "Enter the new track for the departure "
            + "(blank for no track). "
            + (table.getDepartureAt(index).getTrack() != -1
            ? "Current track is " + table.getDepartureAt(index).getTrack()
            : "The departure currently has no track assigned to it"),
        "[1-5]",
        REGEX_TRACK_FORMAT,
        true);

    // If the input is empty, set the delay to none
    int track;
    if (trackString.isEmpty()) {
      track = -1;
      Halt.pressEnterToContinue("The track has been removed from the departure.");

      // If the input is not empty, parse the input and print the new delay
    } else {
      track = Integer.parseInt(trackString);
      Halt.pressEnterToContinue("The track has been changed to " + track);
    }

    // Set the delay of the departure
    table.getDepartureAt(index).setTrack(track);
  }
}
