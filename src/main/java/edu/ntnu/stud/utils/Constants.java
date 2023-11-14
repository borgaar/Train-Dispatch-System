package edu.ntnu.stud.utils;

/**
 * Class for storing constants.
 */
public class Constants {
  public static final String REGEX_24HR_FORMAT = "^([01][0-9]|2[0-3]):[0-5][0-9]$";
  public static final String REGEX_LINE_FORMAT = "^[A-D][1-9]$";
  public static final String REGEX_TRAINID_FORMAT = "^[1-9]\\d{3}$";
  public static final String REGEX_DESTINATION_FORMAT = "^[A-Za-zæøåÆØÅ\\s'-]+$";
  public static final String REGEX_TRACK_FORMAT = "^[1-5]$";
}
