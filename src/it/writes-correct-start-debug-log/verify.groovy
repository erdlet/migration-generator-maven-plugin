File buildLog = new File( basedir, 'build.log' );

assert buildLog.exists()

assert buildLog.text.contains('Starting generation of migration script for: flyway')