package com.rednavis.showcase.impl;

import com.rednavis.showcase.api.Hello;
import javax.ejb.Stateless;

@Stateless
public class HelloImpl implements Hello {

  @Override
  public String hello() {
    return "Hello World!";
  }
}