package de.erdlet.mvn.plugin.migration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LogMessagesTest {
  
  @Test
  public void testStartExecution_ShouldReturnCorrectlyFormattedMessage() {
    assertEquals("Starting generation of migration script for: flyway", LogMessages.startExecution(SupportedTools.FLYWAY));
  }
}
