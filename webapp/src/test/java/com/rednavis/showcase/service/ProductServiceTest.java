package com.rednavis.showcase.service;

import static org.junit.Assert.assertEquals;

import com.rednavis.showcase.BasicCdiTest;
import com.rednavis.showcase.model.Product;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.naming.NamingException;
import org.junit.Test;

@ManagedBean
public class ProductServiceTest extends BasicCdiTest {

  @EJB
  ProductService service;

  @Test
  public void shouldProcessProduct() throws NamingException {

    service.add(new Product("Quentin Tarantino"));
    service.add(new Product("Joel Coen"));
    service.add(new Product("Fargo"));

    List<Product> list = service.get();
    assertEquals("List.size()", 3, list.size());

    for (Product product : list) {
      service.delete(product);
    }

    assertEquals("ProductService.get()", 0, service.get().size());
  }
}