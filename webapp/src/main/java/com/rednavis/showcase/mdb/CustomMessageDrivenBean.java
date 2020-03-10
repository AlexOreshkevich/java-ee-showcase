package com.rednavis.showcase.mdb;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

// http://tomee.apache.org/jmsconnectionfactory-config.html
/*@JMSConnectionFactoryDefinition(
    name="java:global/jms/CustomConnectionFactory",
    user = "sci",
    password = "sci",
    transactional = false
)*/
/*@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "UC/INPUT"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:global/webapp/CustomConnectionFactory")  // connectionFactoryJndiName
})*/
public class CustomMessageDrivenBean implements MessageListener {

  //@Resource
  private MessageDrivenContext mdctx;

  //@PostConstruct
  public void init() {
    System.out.println("Bean created " + CustomMessageDrivenBean.class.getName());
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