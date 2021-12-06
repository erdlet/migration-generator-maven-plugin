package de.erdlet.mvn.plugin.migration;

/**
 * Holds config which is common to potentially all classes. This is e.g. a debug
 * flag.
 */
public final class ConfigHolder {

  private static ConfigHolder INSTANCE;

  public static ConfigHolder getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ConfigHolder();
    }

    return INSTANCE;
  }

  private boolean debug;

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public boolean isDebug() {
    return debug;
  }

  private ConfigHolder() {
  }
}
