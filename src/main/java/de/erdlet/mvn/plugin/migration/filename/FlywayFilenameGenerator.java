package de.erdlet.mvn.plugin.migration.filename;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

import de.erdlet.mvn.plugin.migration.VersionScheme;

/**
 * <p>
 * This filename generator creates names for SQL migrations in Flyway.
 * </p>
 * 
 * <p>
 * <h2>Supported VersionSchemes</h2>
 * The generator is able to handle two version schemes to provide support for
 * the most common
 * use-cases: {@link VersionScheme#NUMBER} and {@link VersionScheme#TIMESTAMP}.
 * The sections below explain how the work and what they assume.
 * </p>
 * 
 * <p>
 * 
 * <h3>VersionScheme#NUMBER</h3>
 * 
 * This scheme checks the target directory resolved from
 * {@link GeneratorContext} for already existing files. It <b>assumes</b>
 * that within this target directory only files with a prefix like
 * {@code V001__...} are existing. In case there are no existing migrations,
 * the generator will start at version {@code V001}, in any other cases it takes
 * the {@code latest version + 1} to generate the new migration file.
 * 
 * <h4>Example for VersionScheme#NUMBER</h4>
 * 
 * {@code V003__my-migration-description.sql}
 * </p>
 * 
 * <p>
 * <h3>VersionScheme#TIMESTAMP</h3>
 * This scheme generates a timestamp with second precision instead of the common
 * Flyway version scheme using numbers.
 * It has the advantage, that in a multi-developer environment the chance of
 * version clashes is reduced, as it is unlikely that two or more developers
 * generate a migration file at the same second.
 * 
 * <h4>Example for VersionScheme#NUMBER</h4>
 * 
 * {@code 20211206120000__my-migration-description.sql}
 * </p>
 */
final class FlywayFilenameGenerator implements Generator {

  private static final String FILENAME_TEMPLATE = "%s__%s.sql";

  private final TimestampProducer timestampProducer;

  FlywayFilenameGenerator(final TimestampProducer timestampProducer) {
    this.timestampProducer = timestampProducer;
  }

  @Override
  public String generate(final GeneratorContext ctx) {
    return ctx.hasVersion() ? formatWithFixVersion(ctx) : formatWithSchema(ctx);
  }

  private String formatWithFixVersion(final GeneratorContext ctx) {
    return String.format(FILENAME_TEMPLATE, ctx.version, ctx.description);
  }

  private String formatWithSchema(final GeneratorContext ctx) {

    switch (ctx.versionSchema) {
      case VersionScheme.TIMESTAMP:
        return formatWithTimestampScheme(ctx);
      case VersionScheme.NUMBER:
        return formatWithNumberScheme(ctx);
      default:
        throw new IllegalArgumentException("Unsupported version scheme: " + ctx.versionSchema);
    }
  }

  private String formatWithTimestampScheme(final GeneratorContext ctx) {
    final String timestamp = timestampProducer.produce();

    return String.format(FILENAME_TEMPLATE, timestamp, ctx.description);
  }

  private String formatWithNumberScheme(final GeneratorContext ctx) {
    try {
      final Path path = Path.of(ctx.targetDir);

      if (!path.toFile().exists()) {
        throw new IllegalArgumentException("Expecting path to exist but does not => " + ctx.targetDir);
      }

      if (path.toFile().isFile()) {
        throw new IllegalArgumentException("Expecting directory path but got file => " + ctx.targetDir);
      }

      final int maxVersion = Arrays.stream(path.toFile().list())
          .map(this::extractVersionNumber)
          .map(Integer::valueOf)
          .max(Comparator.naturalOrder())
          .orElseGet(() -> 0);

      final int nextVersionNumber = maxVersion + 1;
      final String nextVersionString = String.format("V%03d", nextVersionNumber);

      return String.format(FILENAME_TEMPLATE, nextVersionString, ctx.description);

    } catch (final InvalidPathException ex) {
      throw new IllegalArgumentException("Could not parse provided path for targetDir => '" + ctx.targetDir + "'");
    }
  }

  private String extractVersionNumber(final String filename) {
    return filename.split("__")[0].replace("V", "");
  }
}
