[![Build Status](https://travis-ci.com/AlexOreshkevich/java-ee-showcase.svg?branch=master)](https://travis-ci.com/AlexOreshkevich/java-ee-showcase)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# Java EE Showcase
Showcase of features of Java EE Platform (JSP/JSF, Servlets, EJB, JMS, WebServices etc)

# Prerequisites
- Java 1.8 or higher

# How to launch locally
In a case you have Maven installed locally, just type `mvn package tomee:run` or simply `mvn` from project root. Otherwise use wrapper according to your OS.

For Windows, use `./mvnw.cmd clean install tomee:run` to launch the project.

# How to build war file
`mvn package` and then war file would be located on `target/javaee-showcase.war` relative path. 