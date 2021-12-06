package de.erdlet.mvn.plugin.migration.filename;

import java.time.LocalDateTime;

/**
 * Produces a timestamp based on the system clock.
 */
final class SystemTimestampProducer implements TimestampProducer {
  
  @Override
  public String produce() {
    return LocalDateTime.now().format(DEFAULT_FORMATTER);
  }
}
