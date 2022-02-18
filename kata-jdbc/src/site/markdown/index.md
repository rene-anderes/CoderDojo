# Beispielanwendung für JDBC database access

Der Zugriff erfolgt in diesem Beispiel auf eine [Apache Derby Embedded](https://db.apache.org/derby/papers/DerbyTut/embedded_intro.html) Datenbank.

## Build
Die Datenbank mit dem Namen 'derbyEmbeddedDatabase' wird durch den Build automatisch im Root-Verzeichnis des Projekts erstellt.  
   
Die Tabellen können mittels Maven erstellt werden:  
`mvn clean install -P create-tables -DskipTests=true`  
Mittels Maven könne auch alle Tabellen gelöscht werden:  
`mvn clean install -P drop-tables -DskipTests=true`  
  
Wird ein 'mvn clean install' durchgeführt werden alles Test mittels der erwähnten Datenbank durchgeführt. Dazu werden entsprechenden testdaten in die Datenbank geladen. Dieses Testdaten können auch mittels  
`mvn clean install -P insert-testdata -DskipTests=true`  
separat geladen werden.