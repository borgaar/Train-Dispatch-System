package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.DepartureTable;

/**
 * Base class for commands that can be run on the departure table.
 */
public abstract class Command {
  private final String name;

  public Command(String name) {
    this.name = name;
  }

  public abstract void run(DepartureTable table);

  public String getName() {
    return name;
  }
}
