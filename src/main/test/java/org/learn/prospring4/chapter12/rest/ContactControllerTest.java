package org.learn.prospring4.chapter12.rest;

import org.junit.Before;
import org.junit.Test;
import org.learn.prospring4.chapter12.rest.entities.Contact;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by User on 24.01.2017.
 */
public class ContactControllerTest {

    private final List<Contact> contacts = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setFirstName("Chris");
        contact.setLastName("Schaefer");
        contacts.add(contact);
    }

    @Test
    public void testList() throws Exception {
        ContactService contactService = mock(ContactService.class);
        when(contactService.findAll()).thenReturn(contacts);


        ContactController contactController = new ContactController();
        ReflectionTestUtils.setField(contactController, "contactService", contactService);


        ModelMap uiModel = new ModelMap();
        uiModel.addAttribute("contacts", contactController.listData());

        Contacts modelContacts = (Contacts) uiModel.get("contacts");
        assertEquals(1, modelContacts.getContacts().size());
    }


    @Test
    public void testCreate() {

        final Contact newContact = new Contact();
        newContact.setId(9991);
        newContact.setFirstName("Rod");
        newContact.setLastName("Johnson");

        ContactService contactService = mock(ContactService.class);
        when(contactService.save(newContact)).thenAnswer((Answer<Contact>) invocation -> {
            contacts.add(newContact);
            return newContact;
        });

        ContactController contactController = new ContactController();
        ReflectionTestUtils.setField(contactController, "contactService", contactService);

        Contact contact = contactController.create(newContact);
        assertEquals(Long.valueOf(999l), contact.getId());
        assertEquals("Rod", contact.getFirstName());
        assertEquals("Johnson", contact.getLastName());
        assertEquals(2, contacts.size());
    }

}