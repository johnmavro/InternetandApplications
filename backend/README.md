Go to files build.gradle and app.properties <br />
build.gradle is in the current directory <br />
app.properties is in directory ./resources/internet/and/applications<br />
change user and password so that they much one in your system <br />
After this run:<br />
1. ./gradlew flywayMigrate -- installs database<br />
2. Go to mysql-setupqueries and execute fill.sh as described in the README.md of that directory<br />
3. ./gradlew appRun -- from this directory<br />
Server should be operational
