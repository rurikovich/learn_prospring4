package org.learn.prospring4;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by rurik on 16.01.2017.
 */
public class Starter {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

//        getMessagesTest(ctx);
//        getResourcesTest(ctx);
        getEnvironmentTest(ctx);

    }

    private static void getEnvironmentTest(ApplicationContext ctx) {
        ConfigurableEnvironment env = (ConfigurableEnvironment) ctx.getEnvironment();

        MutablePropertySources propertySources = env.getPropertySources();
        Map<String, Object> аррМар = new HashMap<String, Object>();
        аррМар.put("application.home", "application_home");
        propertySources.addLast(new MapPropertySource("PROS PRING4 МАР", аррМар));
        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("JAVA_HOМE: " + System.getenv("JAVA_HOME"));
        System.out.println("user.home: " + env.getProperty("user.home"));
        System.out.println("JAVA_HOМE: " + env.getProperty("JAVA_HOМE"));
        System.out.println("application.home: " + env.getProperty("application.home"));
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
