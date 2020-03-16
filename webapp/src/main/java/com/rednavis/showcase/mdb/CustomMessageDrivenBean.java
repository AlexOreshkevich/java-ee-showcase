package com.rednavis.showcase.mdb;


import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@JMSConnectionFactoryDefinition(
    name = "openejb/Resource/javaee-showcase-webapp/CustomConnectionFactory",
    className = "com.rednavis.showcase.messaging.CustomConnectionFactory",
    user = "sci",
    password = "sci",
    transactional = false)
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "UC/INPUT"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "openejb/Resource/javaee-showcase-webapp/CustomConnectionFactory")
})
public class CustomMessageDrivenBean implements MessageListener {

  @PostConstruct
  public void init() {
    System.out.println("\n\n!!!!Bean created " + getClass().getName() + "\n\n");
  }

  // https://docs.oracle.com/middleware/1213/wls/JMSPG/j2ee.htm#JMSPG911
  @Override
  public void onMessage(Message message) {

    System.out.println("Message received: " + message.toString());

    TextMessage textMessage = (TextMessage) message;
    try {
      System.out.println("Message text value: " + textMessage.getText());
    } catch (JMSException e) {
      System.out.println("Error while trying to consume messages: " + e.getMessage());
    }
  }
}

/*
 activationConfig = {

                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),

                @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),

                @ActivationConfigProperty(propertyName = "user", propertyValue = "quickstartUser"),

                @ActivationConfigProperty(propertyName = "password", propertyValue = "quickstartPwd1!"),

                @ActivationConfigProperty(propertyName = "reconnectAttempts", propertyValue = "-1"),

                @ActivationConfigProperty(propertyName = "setupAttempts", propertyValue = "-1"),

                @ActivationConfigProperty(propertyName="connectorClassName", propertyValue = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory"),

                @ActivationConfigProperty(propertyName="connectionParameters", propertyValue = "host=localhost;port=5455")

        })
 */

/*
@ActivationConfigProperty(propertyName ="connectorClassName", propertyValue = "com.ibm.mq.jms.context.WMQInitialContextFactory"),
       @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
       @ActivationConfigProperty(propertyName ="connectionURL", propertyValue = "10.20.30.40:1414/SYSTEM.DEF.SVRCONN"),
       @ActivationConfigProperty(propertyName="ConnectionFactoryName", propertyValue="MyMQConnectionFactory"),
       @ActivationConfigProperty(propertyName="destination", propertyValue="MyMQQueue")
 */