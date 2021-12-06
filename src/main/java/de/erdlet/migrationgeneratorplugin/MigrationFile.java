/*
 * Copyright © 2021 Tobias Erdle (tobi.erdle@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.erdlet.migrationgeneratorplugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import de.erdlet.migrationgeneratorplugin.filename.Filename;

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
