package com.rednavis.showcase.example;

import javax.ejb.Remote;

@Remote
public interface RemoteBeanInterface {

  void helloRemote();
}
