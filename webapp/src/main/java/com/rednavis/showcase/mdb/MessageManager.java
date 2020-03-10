package com.rednavis.showcase.mdb;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MessageManager {

  @Inject
  private CustomConnectionFactory connectionFactory;

  public CustomConnectionFactory getConnectionFactory() {
    return connectionFactory;
  }
}
