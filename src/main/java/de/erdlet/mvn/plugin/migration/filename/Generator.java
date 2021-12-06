package de.erdlet.mvn.plugin.migration.filename;

/**
 * Generic Interface for the classes generating parts of the migration files.
 */
public interface Generator {

  public String generate(final GeneratorContext ctx);
}
