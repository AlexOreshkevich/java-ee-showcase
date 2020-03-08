package com.rednavis.showcase;

import com.rednavis.showcase.example.CalculatorBean;
import java.io.IOException;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

  // see https://docs.oracle.com/javaee/6/tutorial/doc/gipjf.html
  @EJB
  private CalculatorBean calculatorBean;

  public HelloServlet() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    if (calculatorBean == null) {
      throw new RuntimeException("Unable to initialize bean");
    }

    response.getWriter().printf("Hello from %s", calculatorBean.getClass().getName());
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
