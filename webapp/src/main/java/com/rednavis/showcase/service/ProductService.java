package com.rednavis.showcase.service;

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
import javax.persistence.Query;
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
public class ProductService {

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

  public void add(Product product) {
    entityManager.persist(product);
  }

  public void delete(Product product) {
    entityManager.remove(product);
  }

  @SuppressWarnings("unchecked")
  public List<Product> get() {
    Query query = entityManager.createQuery("SELECT p from Product as p");
    return query.getResultList();
  }

  public void flush() {
    entityManager.flush();
  }
}