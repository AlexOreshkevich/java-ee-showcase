package com.rednavis.showcase.example;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
@Stateless
public class RemoteBean implements RemoteBeanInterface {

  public void helloRemote() {
    System.out.println("Hello from " + getClass().getName());
  }
}
