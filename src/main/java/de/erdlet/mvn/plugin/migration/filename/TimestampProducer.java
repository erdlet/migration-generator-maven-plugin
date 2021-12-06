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
