package com.rednavis.showcase.cdi;

import static org.junit.Assert.assertNotNull;

import com.rednavis.showcase.BasicCdiTest;
import com.rednavis.showcase.messaging.CustomConnectionFactory;
import com.rednavis.showcase.messaging.MessageManager;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import org.junit.Test;

/**
 * Verifies that CDI was configured properly via simple use case.
 *
 * @linkplain https://tomee.apache.org/examples-trunk/cdi-basic/README.html
 */
@ManagedBean
public class ConnectionFactoryInjectionTest extends BasicCdiTest {

  @EJB
  private MessageManager messageManager;

  @Test
  public void test() {

    // Was the EJB injected?
    assertNotNull(messageManager);

    // Was a bean instance injected into message manager?
    final CustomConnectionFactory factory = messageManager.getConnectionFactory();
    assertNotNull(factory);
  }
}