package com.rednavis.showcase;

import com.rednavis.showcase.api.Hello;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/example1")
public class EntryPoint extends HttpServlet {

  @EJB
  private Hello hello;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (PrintWriter printWriter = resp.getWriter()) {
      printWriter.write(hello.hello());
    }
  }
}
