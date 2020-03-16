package com.rednavis.showcase.data;

import com.rednavis.showcase.api.ProductService;
import com.rednavis.showcase.exception.BeanInstantiationException;
import com.rednavis.showcase.model.Product;
import java.sql.DatabaseMetaData;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;

@DataSourceDefinition(
    name = "java:module/datasource",
    className = "org.hsqldb.jdbcDriver",
    url = "jdbc:hsqldb:mem:productDb",
    //portNumber = 1527,
    //serverName = "localhost",
    databaseName = "productDb"
    //user = "examples",
    //password = "examples"
)
@Stateful
public class ProductServiceBean implements ProductService {

  @PersistenceContext(unitName = "persistenceUnit", type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  @Resource(lookup = "java:module/datasource")
  private DataSource dataSource;

  @PostConstruct
  public void display() {
    try {
      DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
      System.out.println("Connecting to database:");
      System.out.println("\turl = " + metaData.getURL());
      System.out.println("\tuser = " + metaData.getUserName());
      System.out.println("\tdatabase = " + metaData.getDatabaseProductName());
      System.out.println("\tdriver = " + metaData.getDriverName());
      System.out.println("\tconnection url = " + metaData.getConnection().getMetaData().getURL());
    }
    catch (Exception e) {
      throw new BeanInstantiationException(e);
    }
  }

  @Override
  public Product save(Product entity) {
    entityManager.persist(entity);
    return entity;
  }

  @Override
  public Product findById(Long id) {
    return entityManager.find(Product.class, id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Product> findAll() {
    return entityManager.createQuery("SELECT p from Product p").getResultList();
  }

  @Override
  public long count() {
    return (Long) entityManager.createQuery("SELECT COUNT(p) from Product p").getSingleResult();
  }

  @Override
  public void deleteById(Long id) {
    Product product = findById(id);
    if (product != null) {
      delete(product);
    }
  }

  @Override
  public void delete(Product entity) {
    entityManager.remove(entity);
  }

  @Override
  public void deleteAll() {
    entityManager.createQuery("DELETE FROM Product p").executeUpdate();
  }
}