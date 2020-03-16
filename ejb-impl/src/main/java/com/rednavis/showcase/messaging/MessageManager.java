package com.rednavis.showcase.messaging;

import com.rednavis.showcase.message.CustomMessageService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class MessageManager implements CustomMessageService {

  @Inject
  private CustomConnectionFactory connectionFactory;

  public CustomConnectionFactory getConnectionFactory() {
    return connectionFactory;
  }

  @Override
  public void send(String data) throws JMSException {

    try (final Connection connection = connectionFactory.createConnection()) {
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      // TODO read from external config file
      Destination destination = session.createQueue("queue:///" + "UC/INPUT");

      final MessageProducer producer = session.createProducer(destination);
      producer.send(session.createTextMessage(data));
    }
  }

  @Override
  public String receive() throws JMSException {

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
          String data = ((TextMessage) message).getText();
          System.out.println("Message Data " + data);
          return data;
        }
      }
    }

    return null;
  }
}
