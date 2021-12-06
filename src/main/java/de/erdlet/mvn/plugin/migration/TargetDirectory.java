package de.erdlet.mvn.plugin.migration;

/**
 * Collection of base dirs where migration tools place their migration files.
 * Thos directories can be overwritten by a Mojo parameter in case someone
 * has a custom directory structure.
 */
public final class TargetDirectory {

  public static final String FLYWAY = "src/main/resources/db/migration";
}
