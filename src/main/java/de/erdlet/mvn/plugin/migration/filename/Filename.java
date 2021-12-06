package de.erdlet.mvn.plugin.migration.filename;

import de.erdlet.mvn.plugin.migration.ConfigHolder;
import de.erdlet.mvn.plugin.migration.SupportedTools;

public final class Filename {

  public static String withInformation(final String tool, final String targetDir, final String versionSchema,
      final String version, final String description) {
    final GeneratorContext ctx = new GeneratorContext(tool, targetDir, versionSchema, version, description);

    switch (tool) {
      case SupportedTools.FLYWAY:
        final TimestampProducer timestampProducer = ConfigHolder.getInstance().isDebug() ? new DummyTimestampProducer()
            : new SystemTimestampProducer();
        return new FlywayFilenameGenerator(timestampProducer).generate(ctx);
      default:
        throw new IllegalArgumentException("Unsupported tool => " + tool);
    }
  }

  private Filename() {
  }
}
