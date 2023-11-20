package edu.ntnu.stud.commands;

import static edu.ntnu.stud.utils.Constants.REGEX_TRACK_FORMAT;
import static edu.ntnu.stud.utils.Constants.REGEX_TRAINID_FORMAT;

import edu.ntnu.stud.exceptions.NoDepartureFoundException;
import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;
import edu.ntnu.stud.utils.Halt;
import edu.ntnu.stud.utils.Search;
import java.util.OptionalInt;

/**
 * Class for setting/changing the track of a departure.
 */
public class SetTrackCommand extends Command {

  InputHandler inputHandler;

  public SetTrackCommand(InputHandler inputHandler) {
    super("Change the track of a departure");
    this.inputHandler = inputHandler;
  }


  @Override
  public void run(DepartureTable table) throws NoDepartureFoundException {
    String trainIdString = inputHandler.getInput(
        "Enter the train ID for the departure you want to change the track of, "
            + "or leave blank to abort",
        "[1000-9999]",
        REGEX_TRAINID_FORMAT,
        true);

    if (trainIdString.isEmpty()) {
      Halt.pressEnterToContinue("Input was empty. Aborting.");
      return;
    }

    int trainId = Integer.parseInt(trainIdString);
    int index = Search.getIndexOfTrainId(table, trainId);

    String trackString = inputHandler.getInput(
        "Enter the new track for the departure "
            + "(blank for no track). "
            + (table.getDepartureList().get(index).getTrack().isPresent()
            ? "Current track is " + table.getDepartureList().get(index).getTrack().getAsInt()
            : "The departure currently has no track assigned to it"),
        "[1-5]",
        REGEX_TRACK_FORMAT,
        true);

    OptionalInt track;
    if (trackString.isEmpty()) {
      track = OptionalInt.empty();
      Halt.pressEnterToContinue("The track has been removed from the departure.");
    } else {
      track = OptionalInt.of(Integer.parseInt(trackString));
      Halt.pressEnterToContinue("The track has been changed to " + track.getAsInt());
    }

    table.getDepartureList().get(index).setTrack(track);
  }
}
