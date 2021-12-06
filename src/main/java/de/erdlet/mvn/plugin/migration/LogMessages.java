package de.erdlet.mvn.plugin.migration;

public final class LogMessages {
  
  /**
   * Formats the log message used for indicating the start of the Mojo execution
   * @param tool the tool for which the script is generated
   * @return the formatted log message
   */
  public static String startExecution(final String tool) {
    return String.format("Starting generation of migration script for: %s", tool);
  }
}
