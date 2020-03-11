package com.rednavis.showcase.mdb;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CustomMessageDrivenBeanTest {

  @Inject
  private CustomConnectionFactory connectionFactory;

  @Before
  public void init() throws NamingException {
    EJBContainer.createEJBContainer().getContext().bind("inject", this);
  }

  @Test
  @Ignore
  public void shouldReceiveMessage() throws Exception {

    try (final Connection connection = connectionFactory.createConnection()) {
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      // TODO read from external config file
      Destination destination = session.createQueue("queue:///" + "UC/INPUT");

      final MessageConsumer consumer = session.createConsumer(destination);

      Message message = consumer.receive(1000);
      if (message == null) {
        System.out.println("No messages are found in the destination queue");
      } else {
        System.out.println("Received " + message);

        if (message instanceof TextMessage) {
          System.out.println("Message Data " + ((TextMessage) message).getText());
        }
      }
    }
  }
}
