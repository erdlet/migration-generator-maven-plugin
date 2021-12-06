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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FlywayFilenameGeneratorTest {

  private static final TimestampProducer MOCK_TIMESTAMP_PRODUCER = new TimestampProducer() {
    public String produce() {
      return LocalDateTime.of(2021, 12, 6, 12, 00, 00).format(DEFAULT_FORMATTER);
    };
  };

  @Rule
  public TemporaryFolder tempTargetDir = TemporaryFolder.builder().assureDeletion().build();

  private final FlywayFilenameGenerator sut = new FlywayFilenameGenerator(MOCK_TIMESTAMP_PRODUCER);

  private String targetDir;

  @Before
  public void setUp() {
    targetDir = tempTargetDir.getRoot().getAbsolutePath();
  }

  @Test
  public void generateFilename_shouldUseFixedVersionIfProvided() {
    assertEquals("V00TEST__my-test.sql", sut.generate(contextWithFixVersion()));
  }

  @Test
  public void generateFilename_shouldUseTimestampInCorrectFormatIfTimestampSchemeIsSelected() {
    assertEquals("20211206120000__my-test.sql", sut.generate(contextWithTimestampScheme()));
  }

  @Test
  public void generateFilename_shouldStartWithV001IfNoMigrationsExistAndNumberSchemeIsSelected() {
    assertEquals("V001__my-test.sql", sut.generate(contextWithNumberScheme()));
  }

  @Test
  public void generateFilename_shouldStartWithV003IfTwoEarlierMigrationsExistAndNumberSchemeIsSelected()
      throws Exception {
    tempTargetDir.newFile("V001__first-migration.sql");
    tempTargetDir.newFile("V002__second-migration.sql");

    assertEquals("V003__my-test.sql", sut.generate(contextWithNumberScheme()));
  }

  @Test
  public void generateFilename_shouldThrowExceptionIfTargetDirDoesNotExist() {
    assertThrows("Expecting path to exist but does not => /foobarbaz", IllegalArgumentException.class,
        () -> sut.generate(contextWithNumberSchemeAndMissingTargetDir()));
  }

  @Test
  public void generateFilename_shouldThrowExceptionIfTargetDirIsFile() throws Exception {
    final File invalidTarget = tempTargetDir.newFile("foobarbaz.boing");

    assertThrows("Expecting directory path but got file => " + invalidTarget.getAbsolutePath(), IllegalArgumentException.class,
        () -> sut.generate(contextWithNumberSchemeAndFileAsTargetDir(invalidTarget)));
  }


  private GeneratorContext contextWithNumberScheme() {
    return context("number", null);
  }

  private GeneratorContext contextWithNumberSchemeAndFileAsTargetDir(final File file) {
    return new GeneratorContext("flyway", file.getAbsolutePath(), "number", null, "my-test");
  }

  private GeneratorContext contextWithNumberSchemeAndMissingTargetDir() {
    return new GeneratorContext("flyway", "/foobarbaz", "number", null, "my-test");
  }

  private GeneratorContext contextWithTimestampScheme() {
    return context("timestamp", null);
  }

  private GeneratorContext contextWithFixVersion() {
    return context("timestamp", "V00TEST");
  }

  private GeneratorContext context(final String versionScheme, final String version) {
    return new GeneratorContext("flyway", targetDir, versionScheme, version, "my-test");
  }
}
