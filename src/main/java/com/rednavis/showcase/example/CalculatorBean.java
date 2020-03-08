package com.rednavis.showcase.example;

import com.rednavis.showcase.registry.PropertyRegistry;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CalculatorBean {

  @EJB
  private PropertyRegistry propertyRegistry;

  public int add(int a, int b) {
    return a + b;
  }

  public int subtract(int a, int b) {
    return a - b;
  }

  public int multiply(int a, int b) {
    return a * b;
  }

  public int divide(int a, int b) {
    return a / b;
  }

  public int remainder(int a, int b) {
    return a % b;
  }

  public void displayProperties() {
    propertyRegistry.displayProperties();
  }
}
