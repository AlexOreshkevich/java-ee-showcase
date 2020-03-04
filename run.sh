#!/bin/bash

docker build --tag=jboss/wildfly-admin .
docker run -it jboss/wildfly-admin