package com.rednavis.showcase.message;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface CustomMessageService {

  void send(String data) throws JMSException;

  String receive() throws JMSException;
}
