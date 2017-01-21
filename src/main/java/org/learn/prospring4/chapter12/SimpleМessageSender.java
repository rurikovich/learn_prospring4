package org.learn.prospring4.chapter12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by User on 21.01.2017.
 */

@Component("messageSender")
public class SimpleМessageSender implements MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String message) {
        jmsTemplate.send(s -> s.createTextMessage(message));
    }
}
