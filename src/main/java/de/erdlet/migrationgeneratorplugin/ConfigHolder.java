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

/**
 * Holds config which is common to potentially all classes. This is e.g. a debug
 * flag.
 */
public final class ConfigHolder {

  private static ConfigHolder INSTANCE;

  public static ConfigHolder getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ConfigHolder();
    }

    return INSTANCE;
  }

  private boolean debug;

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public boolean isDebug() {
    return debug;
  }

  private ConfigHolder() {
  }
}
