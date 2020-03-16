package com.rednavis.showcase.messaging;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.rednavis.showcase.exception.BeanInstantiationException;
import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;

/**
 * Provides custom implementation of {@link ConnectionFactory} based on delegation to IBM MQ {@link JmsConnectionFactory}.
 */
public class CustomConnectionFactory implements ConnectionFactory {

  private JmsConnectionFactory jmsConnectionFactory;

  @PostConstruct
  public void init() {
    try {
      // TODO read from external config file
      jmsConnectionFactory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER).createConnectionFactory();
      jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, "localhost");
      jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_PORT, 1414);
      jmsConnectionFactory.setStringProperty(WMQConstants.USERID, "sci");
      jmsConnectionFactory.setStringProperty(WMQConstants.PASSWORD, "sci");
      jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, "QM1");
      jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, "PASSWORD.SVRCONN");
      jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
    }
    catch (JMSException e) {
      throw new BeanInstantiationException(e);
    }
  }

  @Override
  public Connection createConnection() throws JMSException {
    return jmsConnectionFactory.createConnection();
  }

  @Override
  public Connection createConnection(String userName, String password) throws JMSException {
    return jmsConnectionFactory.createConnection(userName, password);
  }

  @Override
  public JMSContext createContext() {
    return jmsConnectionFactory.createContext();
  }

  @Override
  public JMSContext createContext(String userName, String password) {
    return jmsConnectionFactory.createContext(userName, password);
  }

  @Override
  public JMSContext createContext(String userName, String password, int sessionMode) {
    return jmsConnectionFactory.createContext(userName, password, sessionMode);
  }

  @Override
  public JMSContext createContext(int sessionMode) {
    return jmsConnectionFactory.createContext(sessionMode);
  }
}
