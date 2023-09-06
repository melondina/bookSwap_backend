package de.ait.gr5.bs.utils;

public class StringFormattingForSql {

  public static String formatStringForFullTextSearchSql(String line) {
    if (line != null && !line.equals("")) {
    String[] word = line.split(" ");
    return String.join(" | ", word);
    }
    return null;
  }

}
