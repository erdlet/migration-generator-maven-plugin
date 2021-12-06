File buildLog = new File( basedir, 'build.log' );

assert buildLog.exists()

assert buildLog.text.contains('<description default-value="my-new-migration">')
assert buildLog.text.contains('<versionScheme default-value="number">')
assert buildLog.text.contains('<tool default-value="flyway">')
assert buildLog.text.contains('<targetDir default-value="src/main/resources/db/migration">')