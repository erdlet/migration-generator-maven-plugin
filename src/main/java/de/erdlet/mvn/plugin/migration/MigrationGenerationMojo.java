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

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import de.erdlet.mvn.plugin.migration.util.Strings;

/**
 * The {@link MigrationGenerationMojo} provides the
 * {@code migration-generator:generate} goal
 * which creates the migration script files like defined in the configuration.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class MigrationGenerationMojo extends AbstractMojo {

    /**
     * Defines the tool for which a migration file is used. Defaults to
     * {@link SupportedTools#FLYWAY} and can be overwritten by
     * {@code mvn migration-generator:generate -Dmigration-generator.tool=SOME_TOOL}.
     */
    @Parameter(property = "migration-generator.tool", defaultValue = SupportedTools.FLYWAY)
    private String tool;

    /**
     * Defines the target directory into which the migration file will be placed.
     * Defaults to {@link TargetDirectory#FLYWAY} and can be overwritten by
     * {@code mvn migration-generator:generate -Dmigration-generator.targetDir=SOME_DIR}.
     */
    @Parameter(property = "migration-generator.targetDir", defaultValue = TargetDirectory.FLYWAY)
    private String targetDir;

    /**
     * <p>
     * Defines the version schema which is used to prefix the migration file.
     * Defaults to {@link VersionScheme#NUMBER} and can be overwritten by
     * {@code mvn migration-generator:generate -Dmigration-generator.versionSchema=SOME_SCHEMA}.
     * </p>
     * 
     * <p>
     * <b>ATTENTION:</b> In case {@link MigrationGenerationMojo#version} is set,
     * this schema is ignored.
     * </p>
     */
    @Parameter(property = "migration-generator.versionScheme", defaultValue = VersionScheme.NUMBER)
    private String versionScheme;

    /**
     * <p>
     * Defines the version which is used to prefix the migration file.
     * Can be set by
     * {@code mvn migration-generator:generate -Dmigration-generator.version=SOME_VERSION}.
     * </p>
     * 
     * <p>
     * <b>ATTENTION:</b> In case this is set,
     * {@link MigrationGenerationMojo#versionScheme} is going to be ignored.
     * </p>
     */
    @Parameter(property = "migration-generator.version")
    private String version;

    /**
     * Defines the description of the migrations content.
     * Defaults to {@code my-new-migration} and can be overwritten by
     * {@code mvn migration-generator:generate -Dmigration-generator.description=some-description}.
     */
    @Parameter(property = "migration-generator.description", defaultValue = "my-new-migration")
    private String description;

    @Parameter(property = "migration-generator.debug", defaultValue = "false")
    private boolean debug;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().debug("Starting generation of migration script for tool => " + tool);
        getLog().debug("Selected target directory => " + targetDir);
        getLog().debug("Selected version schema => " + versionScheme);

        final boolean customVersion = Strings.isNotBlank(version);
        getLog().debug("Using custom version? => " + customVersion + "(" + Strings.valueOrEmptyString(version) + ")");
        getLog().debug("Using description for migration => " + description);
        getLog().debug("Is using debug mode => " + debug);

        ConfigHolder.getInstance().setDebug(debug);

        try {
            MigrationFile.of(tool, targetDir, versionScheme, version, description).save();
        } catch (final IOException ex) {
            throw new MojoExecutionException(ex);
        }
    }
}
