package com.rednavis.showcase;

import com.rednavis.showcase.example.CalculatorBean;
import com.rednavis.showcase.trader.Customer;
import java.io.IOException;
import java.util.UUID;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

  private static final String CALCULATOR_SESSION_KEY = "calculator";

  public HelloServlet() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // obtain servlet context (may be empty)
    String servletContext = request.getContextPath();
    System.out.printf("[doGet] context path %s\n", servletContext);
    String jndiBeanLookup = "java:global".concat(servletContext).concat("/CalculatorBean");

    /* See Wildfly logs for JNDI binding details:
    22:15:11,659 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-8) WFLYEJB0473:
      JNDI bindings for session bean named 'CalculatorBean'
       in deployment unit 'deployment "javaee-showcase.war"' are as follows:

        java:global/javaee-showcase/CalculatorBean!com.rednavis.showcase.example.CalculatorBean
        java:app/javaee-showcase/CalculatorBean!com.rednavis.showcase.example.CalculatorBean
        java:module/CalculatorBean!com.rednavis.showcase.example.CalculatorBean
        ejb:/javaee-showcase/CalculatorBean!com.rednavis.showcase.example.CalculatorBean
        java:global/javaee-showcase/CalculatorBean
        java:app/javaee-showcase/CalculatorBean
        java:module/CalculatorBean
     */

    CalculatorBean calculator = (CalculatorBean) request.getSession().getAttribute(CALCULATOR_SESSION_KEY);
    if (calculator == null) {
      // EJB is not yet in the HTTP session
      // This means that the client just sent his first request
      // We obtain a CartBean instance and add it to the session object.
      try {
        InitialContext ic = new InitialContext();
        calculator = (CalculatorBean) ic.lookup(jndiBeanLookup);
        request.getSession().setAttribute(CALCULATOR_SESSION_KEY, calculator);
        System.out.println("Lookup success. CalculatorBean created");

      } catch (NamingException e) {
        throw new ServletException(e);
      }
    }
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
