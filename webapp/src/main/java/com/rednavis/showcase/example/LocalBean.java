package com.rednavis.showcase.example;

import javax.ejb.Stateless;

@javax.ejb.LocalBean
@Stateless
public class LocalBean {

  public void helloLocal() {
    System.out.println("Hello from " + getClass().getName());
  }
}
