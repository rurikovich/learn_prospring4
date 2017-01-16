package org.learn.prospring4;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by rurik on 16.01.2017.
 */
public class Starter {
    public static void main(String[] args) {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("../resources/app-context.xml");


    }
}
