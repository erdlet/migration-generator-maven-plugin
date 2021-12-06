package de.erdlet.mvn.plugin.migration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import de.erdlet.mvn.plugin.migration.filename.Filename;

public final class MigrationFile {

  public static MigrationFile of(final String tool, final String targetDir, final String versionSchema,
      final String version, final String description) {
    return new MigrationFile(Filename.withInformation(tool, targetDir, versionSchema, version, description), targetDir);
  }

  private final String filename;
  private final String targetDir;

  public MigrationFile(final String filename, final String targetDir) {
    this.filename = filename;
    this.targetDir = targetDir;
  }

  public void save() throws IOException {
    final Path migrationFilePath = Path.of(targetDir, filename);
    Files.createFile(migrationFilePath);
  }
}
