package com.rednavis.showcase;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example of redirecting to JSP page from servlet.
 */
@WebServlet(name = "ForwardingServlet", urlPatterns = "/redirect", loadOnStartup = 0)
public class ForwardingServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect(request.getContextPath() + "/forward.jsp");
  }
}
