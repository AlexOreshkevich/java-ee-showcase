package com.rednavis.showcase;

import com.rednavis.showcase.trader.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SessionStorage {

  private static Map<String, Customer> localSessionStorage;

  static {
    localSessionStorage = new HashMap<>();
  }

  public static Customer getModel(String sessionId) {
    return localSessionStorage.get(sessionId);
  }

  public static void addModel(String sessionId, Customer brokerModel){
    localSessionStorage.put(sessionId, brokerModel);
  }

  public static Collection<Customer> getAllModels(){
    return localSessionStorage.values();
  }
}