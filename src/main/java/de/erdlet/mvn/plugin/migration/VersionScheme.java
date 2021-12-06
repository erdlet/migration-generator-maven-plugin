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
