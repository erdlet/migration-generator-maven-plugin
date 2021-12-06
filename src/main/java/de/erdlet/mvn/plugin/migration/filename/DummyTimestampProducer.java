package de.erdlet.mvn.plugin.migration.filename;

/**
 * Dummy implementation which can be used in case the plugin's debug mode is
 * enabled.
 */
public final class DummyTimestampProducer implements TimestampProducer {

  @Override
  public String produce() {
    return "20211206120000";
  }
}
