package com.rednavis.showcase;

import com.rednavis.showcase.api.ProductService;
import com.rednavis.showcase.exception.BeanInstantiationException;
import com.rednavis.showcase.model.Product;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

  @EJB
  private ProductService productService;

  @PostConstruct
  public void init() {
    if (productService == null) {
      throw new BeanInstantiationException();
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

    StringBuilder builder = new StringBuilder();
    for (Product product : productService.findAll()) {
      builder.append(product.toString());
    }

    response.getWriter().printf("List of products:\n%s", builder.toString());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    productService.save(new Product(UUID.randomUUID().toString()));
  }
}
