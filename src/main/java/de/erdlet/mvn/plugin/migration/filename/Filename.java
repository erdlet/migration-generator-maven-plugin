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
