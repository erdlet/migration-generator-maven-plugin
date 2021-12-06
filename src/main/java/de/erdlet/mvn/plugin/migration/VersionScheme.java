package de.erdlet.mvn.plugin.migration;

/**
 * Definition of the version a migration script has. Most of the tools use
 * something like a number (e.g. {@code V001}) or a timestamp
 * (e.g. {@code 20211206110056}) as unique identifier for a migration.
 * Nevertheless, one may want to use none of those schemas,
 * so the {@link MigrationGenerationMojo} has a parameter which allows to set
 * the version manually which bypasses this predefined schema.
 */
public final class VersionScheme {

  public static final String NUMBER = "number";
  public static final String TIMESTAMP = "timestamp";
}
