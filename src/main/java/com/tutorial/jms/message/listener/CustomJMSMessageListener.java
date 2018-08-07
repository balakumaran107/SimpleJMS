package com.tutorial.jms.message.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class CustomJMSMessageListener implements MessageListener {

  /**
   * default message handling
   */
  public void onMessage(Message message) {

    TextMessage textMessage = (TextMessage) message;
    try {
      System.out.println(
          textMessage.getJMSMessageID() + "|\t\t\t" + textMessage.getJMSTimestamp() + "|\t\t\t"
              + textMessage.getText()+ "|\t\t\t" + textMessage.getJMSPriority());
      textMessage.acknowledge();
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
