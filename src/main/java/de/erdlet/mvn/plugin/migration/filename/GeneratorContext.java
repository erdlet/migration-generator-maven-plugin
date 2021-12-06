package de.erdlet.mvn.plugin.migration.filename;

import de.erdlet.mvn.plugin.migration.util.Strings;

/**
 * Context class containing all necessary information for the underlaying generators.
 */
final class GeneratorContext {

  final String tool;
  final String targetDir;
  final String versionSchema;
  final String version;
  final String description;

  GeneratorContext(final String tool, final String targetDir, final String versionSchema, final String version,
      final String description) {
    this.tool = tool;
    this.targetDir = targetDir;
    this.versionSchema = versionSchema;
    this.version = version;
    this.description = description;
  }

  boolean hasVersion() {
    return Strings.isNotBlank(version);
  }
}
