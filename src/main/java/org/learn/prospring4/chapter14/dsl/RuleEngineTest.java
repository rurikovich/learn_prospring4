package org.learn.prospring4.chapter14.dsl;

import org.joda.time.DateTime;
import org.learn.prospring4.chapter14.dsl.pojo.ContactDSL;
import org.learn.prospring4.chapter14.dsl.service.ContactDSLService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.joda.time.format.DateTimeFormat.forPattern;

/**
 * Created by User on 26.01.2017.
 */
public class RuleEngineTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/dsl/dsl-app-context.xml");
        ContactDSLService contactService = ctx.getBean("contactDSLService", ContactDSLService.class);
        ContactDSL contact = createContactDSL(contactService);
        contactService.applyRule(contact);
        System.out.println("Contact: " + contact);
        try {
            System.in.read();
        } catch (Exception ех) {
            ех.printStackTrace();
        }
        contactService.applyRule(contact);
        System.out.println("Contact: " + contact);
    }

    private static ContactDSL createContactDSL(ContactDSLService contactService) {
        ContactDSL contact = new ContactDSL();
        contact.setId(1l);
        contact.setFirstName("Chris");
        contact.setLastName("Schaefer");
        DateTime birthDate = forPattern("yyyy-MM-dd").parseDateTime("2010-05-03");
        contact.setBirthDate(birthDate);
        return contact;
    }
}
