package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.InputHandler;
import edu.ntnu.stud.models.DepartureTable;

/**
 * Base class for commands that can be run on the departure table.
 */
public abstract class Command {
  private final String name;
  public final InputHandler inputHandler;

  public Command(String name) {
    this.name = name;
    inputHandler = new InputHandler();
  }

  public abstract void run(DepartureTable table) throws Exception;

  public String getName() {
    return name;
  }
}
