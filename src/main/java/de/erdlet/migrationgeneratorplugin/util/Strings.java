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
package de.erdlet.migrationgeneratorplugin.util;

public final class Strings {
  
  private static final String EMPTY_STRING = "";

  public static boolean isBlank(final String value) {
    return value == null || value.trim().isEmpty();
  }

  public static boolean isNotBlank(final String value) {
    return value != null && !value.trim().isEmpty();
  }

  public static String valueOrEmptyString(final String value) {
    return isBlank(value) ? EMPTY_STRING : value;
  }

  private Strings() {}
}
