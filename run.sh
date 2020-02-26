#!/bin/bash

# https://blogs.oracle.com/theaquarium/glassfish-docker-images-%e2%80%93-update
docker run -ti -e ADMIN_PASSWORD=admin \
  -p 4848:4848 \
  -p 8080:8080 \
  -p 8181:8181 \
  -d showcase:1.0