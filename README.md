# Java EE Showcase
Showcase of features of Java EE Platform (JSP/JSF, Servlets, EJB, JMS, WebServices etc)

# Prerequisites
- Java 1.8 or higher

# How to launch locally
In a case you have Maven installed locally, just type `mvn package tomee:run` or simply `mvn` from project root. Otherwise use wrapper according to your OS.

For Windows, use `./mvnw.cmd clean install tomee:run` to launch the project.

# How to build war file
`mvn package` and then war file would be located on `target/javaee-showcase.war` relative path. 