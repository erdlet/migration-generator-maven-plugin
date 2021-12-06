package de.erdlet.mvn.plugin.migration;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The {@link MigrationGenerationMojo} provides the {@code migration-generator:generate} goal
 * which creates the migration script files like defined in the configuration.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class MigrationGenerationMojo extends AbstractMojo {

    @Parameter(name = "tool", defaultValue = SupportedTools.FLYWAY)
    private String tool;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().debug(LogMessages.startExecution(tool));
    }
}
