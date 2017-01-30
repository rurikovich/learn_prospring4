package org.learn.prospring4.chapter16.mvc.client;

import org.joda.time.DateTime;
import org.learn.prospring4.chapter16.mvc.Contacts;
import org.learn.prospring4.chapter16.mvc.entities.Contact;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * Created by User on 24.01.2017.
 */
public class RestClient {

    private static final String URL_GET_ALL_CONTACTS =
            "http://localhost:8080/restful/contact/listdata";
    private static final String URL_GET_CONTACT_BY_ID =
            "http://localhost:8080/restful/contact/{id}";
    private static final String URL_CREATE_CONTACT =
            "http://localhost:8080/restful/contact/";
    private static final String URL_UPDATE_CONTACT =
            "http://localhost:8080/restful/contact/{id}";
    private static final String URL_DELETE_CONTACT =
            "http://localhost:8080/restful/contact/{id}";
    public static final int ID = 2;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:restful-client-app-context.xml");
        ctx.refresh();
        Contact contact;
        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
        listContacts(restTemplate);

        createContact(restTemplate, "James", "Gosling");


        contact = fetchContact(restTemplate, ID);
        System.out.println(contact);
        System.out.println("");

        contact = fetchContact(restTemplate, ID);
        contact.setFirstName("John Doe");
        System.out.println("Testing update contact bу id :");
        restTemplate.put(URL_UPDATE_CONTACT, contact, 1);
        System.out.println("Contact update successfully: " + contact);
        System.out.println("");

        restTemplate.delete(URL_DELETE_CONTACT, ID);
        System.out.println("Testing delete contact bу id : ");
        listContacts(restTemplate);
    }

    private static Contact fetchContact(RestTemplate restTemplate, int id) {
        Contact contact;
        System.out.println("Testing retrieve а contact bу id :");
        contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, id);
        return contact;
    }

    private static void createContact(RestTemplate restTemplate, String firstName, String lastName) {
        System.out.println("Testing create contact :");
        Contact contactNew = new Contact();
        contactNew.setId(-1);
        contactNew.setVersion(1);
        contactNew.setFirstName(firstName);
        contactNew.setLastName(lastName);
        contactNew.setBirthDate(new DateTime());
        contactNew = restTemplate.postForObject(URL_CREATE_CONTACT, contactNew, Contact.class);
        System.out.println("Contact created success fully: " + contactNew);
    }

    private static void listContacts(RestTemplate restTemplate) {
        System.out.println("Testing retrieve all contacts:");
        Contacts contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
        listContacts(contacts);
    }

    private static void listContacts(Contacts contacts) {
        for (Contact contact : contacts.getContacts())
            System.out.println(contact.toString());
        System.out.println("");
    }

}
