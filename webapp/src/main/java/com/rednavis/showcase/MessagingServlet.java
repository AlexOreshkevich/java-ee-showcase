package com.rednavis.showcase;

import com.google.common.base.Strings;
import com.rednavis.showcase.exception.BeanInstantiationException;
import com.rednavis.showcase.message.CustomMessageService;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/message", loadOnStartup = 1)
public class MessagingServlet extends HttpServlet {

  @EJB
  CustomMessageService messageManager;

  @PostConstruct
  public void init() {
    if (messageManager == null) {
      throw new BeanInstantiationException();
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    try {
      String message = messageManager.receive();
      resp.getWriter().printf("Received message %s", message);
    } catch (JMSException e) {
      throw new ServletException(e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
    String message = req.getQueryString();
    if (Strings.isNullOrEmpty(message)) {
      message = new Date().toString();
    }
    try {
      messageManager.send(message);
    } catch (JMSException e) {
      throw new ServletException(e);
    }
  }
}
