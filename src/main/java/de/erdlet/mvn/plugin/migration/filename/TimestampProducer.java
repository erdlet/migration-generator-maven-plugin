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

import java.time.format.DateTimeFormatter;

/**
 * Interface for defining a provider which creates timestamps as string.
 */
interface TimestampProducer {

  final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  /**
   * Produces the timestamp based on either the {@link #DEFAULT_FORMATTER} or
   * something implementation specific.
   * 
   * @return the formatted timestamp string
   */
  public String produce();
}
