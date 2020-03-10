package com.rednavis.showcase.cdi;

import static org.junit.Assert.assertNotNull;

import com.rednavis.showcase.mdb.CustomConnectionFactory;
import com.rednavis.showcase.mdb.MessageManager;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Verifies that CDI was configured properly via simple use case.
 *
 * @linkplain https://tomee.apache.org/examples-trunk/cdi-basic/README.html
 */
public class ConnectionFactoryInjectionTest {

  private static EJBContainer container;

  @EJB
  private MessageManager messageManager;

  @BeforeClass
  public static void start() {
    container = EJBContainer.createEJBContainer();
  }

  @Before
  public void setUp() throws Exception {
    container.getContext().bind("inject", this);
  }

  @Test
  public void test() {

    // Was the EJB injected?
    assertNotNull(messageManager);

    // Was a bean instance injected into message manager?
    final CustomConnectionFactory factory = messageManager.getConnectionFactory();
    assertNotNull(factory);
  }

  @AfterClass
  public static void stop() {
    container.close();
  }
}