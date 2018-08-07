package com.tutorial.jms.provider;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.TopicConnection;
import javax.naming.NamingException;

public interface JMSConnectionProvider {

  /**
   * Defines the JNDI context factory.
   */
  String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

  /**
   * Defines the JNDI context factory.
   */
  String WEBLOGIC_URL = "t3://localhost:7001";

  /**
   * Defines the JMS context factory.
   */
  String JMS_FACTORY = "jms/myConnectionFactory";
  /**
   * Defines the queue.
   */
  String QUEUE = "jms/myQeue";

  /**
   * get JMS QueueConnection
   *
   * @return getJMSQeueConnection
   */
  QueueConnection getJMSQueueConnection() throws JMSException, NamingException;

  /**
   * get JMS topic connection
   *
   * @return getJMSTopicConnection
   */
  TopicConnection getJMSTopicConnection();

  /**
   * get Queue from the initital context
   * @return
   */
  Queue getQueue() throws NamingException;
}
