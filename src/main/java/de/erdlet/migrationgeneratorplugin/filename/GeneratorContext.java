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
package de.erdlet.migrationgeneratorplugin.filename;

import de.erdlet.migrationgeneratorplugin.util.Strings;

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
