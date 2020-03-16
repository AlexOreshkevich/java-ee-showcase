package com.rednavis.showcase.dashboard;

import com.rednavis.showcase.message.CustomMessageService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class DashboardServlet extends HttpServlet {

  @EJB
  CustomMessageService messageManager;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    try {
      String message = messageManager.receive();
      resp.getWriter().printf("Received message %s", message);
    } catch (JMSException e) {
      resp.getWriter().printf("ERROR %s", e.getMessage());
      e.printStackTrace();
    }
  }
}
