# Checkout du code via le serveur svn
svn checkout http://automatic-discover-of-datacenter.googlecode.com/svn/trunk/ .

# Compilation du code (prend du temps la premiere fois)
mvn clean install

# Execution du programme
cd target
unzip automatic-discover-of-datacenter-1.0-SNAPSHOT-executable.zip 
cd automatic-discover-of-datacenter-1.0-SNAPSHOT
java -jar automatic-discover-of-datacenter-1.0-SNAPSHOT.jar
