# NetworkConfigurationService
JEE7 and WildFly 15 Project

## How to Run
It is recommended to use the provided version of Wildfly included inside tools/
The pom.xml is also configured to deploy the project to that specific Wildfly.

### Run Wildfly
```
tools/wildfly/bin/standalone.sh 	(on Linux)
tools/wildfly/bin/standalone.bat	(on Windows)
```

### Run DerbyDB
```
java -jar tools/db-derby-10.5.3.0-bin/lib/derbyrun.jar server start -p 50000
```

## Project Description
A project used to demo a JEE application deployed on a Wildfly server.
Features:
  *REST
  *JPA
    *Hibernate
    *Own implementation of a cache
  *EJB
    *Beans
    *Security
  *CDI
    *Interceptors
    *Events
