package com.rednavis.showcase;

import com.rednavis.showcase.trader.BrokerModel;
import com.rednavis.showcase.trader.BrokerModelImpl;
import com.rednavis.showcase.trader.Customer;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

  static int instanceCount = 0;

  public HelloServlet(){
    instanceCount++;
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();

    if (session.isNew()) {
      SessionStorage.addModel(session.getId(), generate(session.getId()));
      session.setAttribute("customer", SessionStorage.getModel(session.getId()));
    }

    // display all customers
    SessionStorage.getAllModels().forEach(customer -> {
      try {
        response.getWriter().println(customer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private Customer generate(String sessionId){
    return new Customer(sessionId, UUID.randomUUID().toString().substring(0, 3), UUID.randomUUID().toString().substring(0, 3));
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String name = request.getParameter("name");
    if (name == null) {
      name = "World";
    }
    request.setAttribute("user", name);
    request.getRequestDispatcher("forward.jsp").forward(request, response);
  }
}
