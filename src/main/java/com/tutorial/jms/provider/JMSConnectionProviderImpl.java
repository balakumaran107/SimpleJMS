package com.tutorial.jms.provider;

import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.TopicConnection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSConnectionProviderImpl implements JMSConnectionProvider {

  /**
   * get qeue connection
   *
   * @return qeue connection
   */
  public QueueConnection getJMSQueueConnection() throws JMSException, NamingException {
    QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) getInitialContext()
        .lookup(JMSConnectionProvider.JMS_FACTORY);
    return queueConnectionFactory.createQueueConnection();
  }

  /**
   * get topic connection
   *
   * @return topic connection
   */
  public TopicConnection getJMSTopicConnection() {
    throw new UnsupportedOperationException();
  }

  /**
   * get Queue from initial context
   */
  public Queue getQueue() throws NamingException {
    return (Queue) getInitialContext().lookup(JMSConnectionProvider.QUEUE);
  }

  /**
   * get initial context
   *
   * @return InitialContext
   */
  private static InitialContext getInitialContext() throws NamingException {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JMSConnectionProvider.JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, JMSConnectionProvider.WEBLOGIC_URL);
    return new InitialContext(env);
  }
}
