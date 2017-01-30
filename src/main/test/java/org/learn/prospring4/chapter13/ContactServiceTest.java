package org.learn.prospring4.chapter13;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.prospring4.chapter16.mvc.ContactService;
import org.learn.prospring4.chapter16.mvc.entities.Contact;
import org.learn.prospring4.chapter13.annotations.DataSets;
import org.learn.prospring4.chapter13.annotations.ServiceTestExecutionListener;
import org.learn.prospring4.chapter13.config.ServiceTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rurik on 25.01.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class ContactServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    public static final String CONTACT_SERVICE_TEST_DATA_XLS = "/org/learn/prospring4/chapter13/data/ContactServiceimplTest.xls";

    @Autowired
    ContactService contactService;

    @PersistenceContext
    private EntityManager em;

    @DataSets(setUpDataSet = CONTACT_SERVICE_TEST_DATA_XLS)
    @Test
    public void testFindAll() throws Exception {
        List<Contact> result = contactService.findAll();
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    @DataSets(setUpDataSet = CONTACT_SERVICE_TEST_DATA_XLS)
    @Test
    public void testFindByFirstNameAndLastName_l() throws Exception {
        List<Contact> result = contactService.findByFirstNameAndLastName("Chris", "Schaefer");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }


    @DataSets(setUpDataSet = CONTACT_SERVICE_TEST_DATA_XLS)
    @Test
    public void testFindByFirstNameAndLastName_2() throws Exception {
        List<Contact> result = contactService.findByFirstNameAndLastName("Peter", "Chan");
        assertTrue(result.isEmpty());
    }


    @Test
    public void testAddContact() throws Exception {
        deleteFromTables("CONTACT");
        Contact contact = new Contact();
        contact.setFirstName("Rod");
        contact.setLastName("Johnson");
        contact.setVersion(1);
        contactService.save(contact);
        em.flush();
        List<Contact> contacts = contactService.findAll();
        assertEquals(1, contacts.size());
    }

    @Test(expected = DataAccessException.class)
    public void testAddContactWithJSR349Error() throws Exception {
        deleteFromTables("CONTACT");
        Contact contact = new Contact();
        contactService.save(contact);
        em.flush();
        List<Contact> contacts = contactService.findAll();
        assertEquals(0, contacts.size());
    }

}
