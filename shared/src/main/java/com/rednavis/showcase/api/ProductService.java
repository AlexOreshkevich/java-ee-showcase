package com.rednavis.showcase.api;

import com.rednavis.showcase.data.CrudRepository;
import com.rednavis.showcase.model.Product;
import javax.ejb.Remote;

@Remote
public interface ProductService extends CrudRepository<Product, Long> {
}