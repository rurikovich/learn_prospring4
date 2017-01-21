package org.learn.prospring4.chapter12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by User on 21.01.2017.
 */
public class SimpleMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageListener.class);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info("Message recei ved: " + textMessage.getText());
        } catch (JMSException e) {
            logger.error("Jms error", e);
        }
    }
}
