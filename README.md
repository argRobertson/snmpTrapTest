# snmpTrapTest
Build instructions:
- verify that both java and maven are installed on your system and set up correctly (```java --version``` and ```mvn --version``` will check for them respectively)
- run the command ```mvn package``` on the command line to create the executable jar file snmp-prefix-matcher-1.0-SNAPSHOT-jar-with-dependencies.jar in the target folder

- If you want to add or remove trap-type-oid prefixes you must edit the file snmp.yml in src/main/resources/ save the changes made and re-run the ```mvn package``` command

Run instructions:
- in the target folder run the command ```snmp-prefix-matcher-1.0-SNAPSHOT-jar-with-dependencies.jar``` this will start the application
- to exit the program simply enter "exit" into the console.