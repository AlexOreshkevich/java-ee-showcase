package com.rednavis.showcase;

import com.rednavis.showcase.model.Product;
import com.rednavis.showcase.service.ProductService;
import java.io.IOException;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

  @EJB
  private ProductService productService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

    StringBuilder builder = new StringBuilder();
    for (Product product : productService.get()){
      builder.append(product.toString());
    }

    response.getWriter().printf("List of products:\n%s", builder.toString());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    productService.add(new Product(UUID.randomUUID().toString()));
  }
}
