package com.rednavis.showcase.exception;

/**
 * Indicates that there were issues during CDI and/or bean instantiation.
 */
public class BeanInstantiationException extends RuntimeException {

  public BeanInstantiationException() {
  }

  public BeanInstantiationException(String message) {
    super(message);
  }

  public BeanInstantiationException(String message, Throwable cause) {
    super(message, cause);
  }

  public BeanInstantiationException(Throwable cause) {
    super(cause);
  }

  public BeanInstantiationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
