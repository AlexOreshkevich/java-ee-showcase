package com.rednavis.showcase.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.rednavis.showcase.BasicCdiTest;
import com.rednavis.showcase.api.ProductService;
import com.rednavis.showcase.model.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import org.junit.Test;

@ManagedBean
public class ProductServiceBeanTest extends BasicCdiTest {

  @EJB
  ProductService productService;

  @Test
  public void shouldSaveAndRetrieveProducts() {

    assertNotNull(productService);

    Product first = new Product("Quentin Tarantino");
    Product second = new Product("Joel Coen");
    Product third = new Product("Joel Coen");

    productService.saveAll(Arrays.asList(first, second, third));

    assertEquals(3, productService.count());
    assertEquals(3, new ArrayList<Product>((Collection<? extends Product>) productService.findAll()).size());

    productService.deleteAll();

    assertEquals(0, productService.count());
  }
}