package com.rednavis.showcase;

import com.rednavis.showcase.mdb.MessageManager;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/message", loadOnStartup = 1)
public class MessagingServlet extends HttpServlet {

  @EJB
  MessageManager messageManager;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (final Connection connection = messageManager.getConnectionFactory().createConnection()) {
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      // TODO read from external config file
      Destination destination = session.createQueue("queue:///" + "UC/INPUT");

      final MessageProducer producer = session.createProducer(destination);
      producer.send(session.createTextMessage(req.getQueryString() + " at " + new Date().toGMTString()));
      resp.getWriter().printf("Message was sent successfully");
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
  }
}
