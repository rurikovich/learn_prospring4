package org.learn.prospring4.chapter12.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by User on 21.01.2017.
 */
public class JmsStarter {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jms-listener-app-context.xml","jms-sender-app-context.xml");

        MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);
        for (int i = 0; i < 10; i++) {
            messageSender.sendMessage("Test message: " + i);
        }
    }

}
