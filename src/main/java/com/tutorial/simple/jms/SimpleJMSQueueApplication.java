package com.tutorial.simple.jms;

import com.tutorial.jms.message.listener.CustomJMSMessageListener;
import com.tutorial.jms.provider.JMSConnectionProvider;
import com.tutorial.jms.provider.JMSConnectionProviderImpl;
import java.util.Enumeration;
import java.util.UUID;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

public class SimpleJMSQueueApplication {

  public static void main(String args[]) throws JMSException {
    JMSConnectionProvider jmsConnectionProvider = new JMSConnectionProviderImpl();
    QueueConnection queueConnection = null;
    QueueSession queueSession = null;
    QueueSender queueSender = null;
    QueueBrowser queueBrowser = null;
    QueueReceiver queueReceiver = null;
    try {
      queueConnection = jmsConnectionProvider.getJMSQueueConnection();
      queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
      queueSender = queueSession.createSender(jmsConnectionProvider.getQueue());
      queueConnection.start();
      TextMessage textMessage = queueSession.createTextMessage();
      textMessage.setText("I am so cool - " + UUID.randomUUID());
      queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      queueSender.send(textMessage);

      queueReceiver = queueSession.createReceiver(jmsConnectionProvider.getQueue());
      queueReceiver.setMessageListener(new CustomJMSMessageListener());
      Thread.sleep(1000);

      queueBrowser = queueSession.createBrowser(jmsConnectionProvider.getQueue());
      Enumeration enumeration = queueBrowser.getEnumeration();
      TextMessage msg;
      while (enumeration.hasMoreElements()) {
        msg = (TextMessage) enumeration.nextElement();
        System.out.println(msg.getJMSMessageID()+"\t\t\t"+msg.getJMSTimestamp()+"\t\t\t"+msg.getText());
      }
    } catch (JMSException e) {
      e.printStackTrace();
    } catch (NamingException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      if (queueSender != null) {
        queueSender.close();
      }
      if (queueSession != null) {
        queueSession.close();
      }
      if (queueConnection != null) {
        queueConnection.close();
      }
      if (queueBrowser != null) {
        queueBrowser.close();
      }
      if (queueReceiver != null) {
        queueReceiver.close();
      }
    }
  }
}
