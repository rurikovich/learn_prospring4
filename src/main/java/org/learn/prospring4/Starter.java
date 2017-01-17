package org.learn.prospring4;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Locale;

/**
 * Created by rurik on 16.01.2017.
 */
public class Starter {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

//        getMessagesTest(ctx);

        getResourcesTest(ctx);

    }

    private static void getMessagesTest(ApplicationContext ctx) {
        Locale english = Locale.ENGLISH;
        Locale czech = new Locale("cs", "CZ");
        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, czech));

        System.out.println(ctx.getMessage("nameMsg", new Object[]{"Chris", "Schaefer"}, english));
    }

    private static void getResourcesTest(ApplicationContext ctx) throws Exception {
        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();
        System.out.println(file.getPath());
        Resource resl = ctx.getResource("file://" + file.getPath());
        displayinfo(resl);
        Resource res2 = ctx.getResource("classpath:test.txt");
        displayinfo(res2);
//        Resource resЗ = ctx.getResource("http://www.google.com");
//        displayinfo(resЗ);
    }

    private static void displayinfo(Resource res) throws Exception {
        System.out.println(res.getClass());
        System.out.println(res.getURL().getContent());
        System.out.println("");
    }

}
