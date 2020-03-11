package com.rednavis.showcase;


import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Base class for all tests that are based on CDI for embedded container.
 */
public abstract class BasicCdiTest {

  private static EJBContainer container;

  @BeforeClass
  public static void start() {
    container = EJBContainer.createEJBContainer();
  }

  @Before
  public void setUp() throws Exception {
    container.getContext().bind("inject", this);
  }

  @AfterClass
  public static void stop() {
    container.close();
  }
}