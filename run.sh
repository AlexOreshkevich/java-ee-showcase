#!/bin/bash

docker build --rm=true --tag=jboss/wildfly-admin .
docker run -p 8080:8080 -p 9990:9990 -it jboss/wildfly-admin