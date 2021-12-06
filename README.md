# migration-generator-maven-plugin

This plugin does the tedious work of creating the files for new database migrations by hand. In the future it will hopefully support several tools like Flyway, MyBatis Migrations or Liquibase.

## Current TODOs

- [ ] Build release
- [ ] Push to Maven Central

## General usage

To use the plugin just add it into your `pom.xml` like this:

```
<plugin>
  <groupId>de.erdlet</groupId>
  <artifactId>migration-generator-maven-plugin</artifactId>
  <version>${current.plugin.version}</version>
</plugin>
```

This will help you a lot in case you're a Flyway user, as Flyway is the current standard tool of the plugin. Later there may be other
tools and more settings may be required.

With this setup you can run `mvn migration-generator:generate` and receive a file like `src/main/resources/db/migration/V001__my-new-migration.sql` as
a result. To get a custom description, just run `mvn migration-generator:generate -Dmigration-generator.description='just-my-description'` and receive
a file like this: `src/main/resources/db/migration/V001__just-my-description.sql`.

### Use timestamps as version

Especially in teams with more developers the default versioning scheme of Flyway using `V000__` can lead to trouble as version numbers can be doubled
and merge conflicts are going to be daily business. Instead of this, the plugin can use a generated timestamp as identifier for the migration.
Just use this configuration to switch the versioning scheme:

```
<plugin>
  <groupId>de.erdlet</groupId>
  <artifactId>migration-generator-maven-plugin</artifactId>
  <version>${current.plugin.version}</version>
  <configuration>
    <versionScheme>timestamp</versionScheme>
  </configuration>
</plugin>
```

When you run `mvn migration-generator:generate`, you'll get a file like this: `src/main/resources/db/migration/20211206123354__my-new-migration.sql`. The
chance that two people generating a migration at the same second is reeeeeally low, so this should prevent a lot of merge conflicts.

### Setting different target directory

Sometimes a project doesn't use the default directory for its migration files. No problem for this plugin. Just change the `targetDir` configuration
to your favorite location:

```
<plugin>
  <groupId>de.erdlet</groupId>
  <artifactId>migration-generator-maven-plugin</artifactId>
  <version>${current.plugin.version}</version>
  <configuration>
    <targetDir>src/main/resources/migrations</targetDir>
  </configuration>
</plugin>
```

When you run `mvn migration-generator:generate`, you'll get a file like this: `src/main/resources/migrations/V001__my-new-migration.sql`.

### Setting fix version for new migration

Maybe someone wants to have a specific version for the migration file, ignoring all versioning schemes. This plugin supports this by using
the following setup and commands on the CLI:

```
<plugin>
  <groupId>de.erdlet</groupId>
  <artifactId>migration-generator-maven-plugin</artifactId>
  <version>${current.plugin.version}</version>
</plugin>
```

When you run `mvn migration-generator:generate -Dmigration-generator.version=VFIX`, you'll get a file like this: `src/main/resources/migrations/VFIX__my-new-migration.sql`.

## License

This project is licensed under the Apache 2.0 License. For more details please check the [LICENSE file](./LICENSE).