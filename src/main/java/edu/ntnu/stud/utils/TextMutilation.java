package edu.ntnu.stud.utils;

/**
 * A class for mutilating/formatting text.
 */
public class TextMutilation {

  /**
   * Method for formatting a string to a given length.
   *
   * @param entry        The entry to format
   * @param targetLength The length to format the entry to
   * @param leftAlign    Whether to left align the entry or not
   * @return The formatted entry
   */
  public static String formatToLength(String entry, int targetLength, boolean leftAlign) {
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
  public static String formatToTableEntry(String entry,
                                          int targetLength,
                                          boolean lastColumn,
                                          boolean leftAlign) {

    String formattedEntry = formatToLength(entry, targetLength, leftAlign);
    formattedEntry = formattedEntry.length() < targetLength ? formattedEntry + " " : formattedEntry;
    formattedEntry = lastColumn ? formattedEntry + " ┃" : formattedEntry + " ";

    return "┃ " + formattedEntry;
  }
}
