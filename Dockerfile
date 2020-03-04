# Example of multi-staged build
# First stage used for building artifacts from application source codes
# Second stage is used for deployment

FROM jboss/wildfly

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#12345 --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]