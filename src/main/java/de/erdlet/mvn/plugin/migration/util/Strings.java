package de.erdlet.mvn.plugin.migration.util;

public final class Strings {
  
  private static final String EMPTY_STRING = "";

  public static boolean isBlank(final String value) {
    return value == null || value.trim().isEmpty();
  }

  public static boolean isNotBlank(final String value) {
    return value != null && !value.trim().isEmpty();
  }

  public static String valueOrEmptyString(final String value) {
    return isBlank(value) ? EMPTY_STRING : value;
  }

  private Strings() {}
}
