package de.erdlet.mvn.plugin.migration;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * The {@link MigrationGenerationMojo} provides the {@code migration-generator:generate} goal
 * which creates the migration script files like defined in the configuration.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class MigrationGenerationMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        // TODO Auto-generated method stub
        
    }
}
