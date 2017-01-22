package org.learn.prospring4.chapter12.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by User on 22.01.2017.
 */

@Controller
@RequestMapping(value = "/contact")
public class ContactController {
    final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Contacts listData() {
        return new Contacts(contactService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact findContactByid(@PathVariable Long id) {
        return contactService.findByid(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Contact create(@RequestBody Contact contact) {
        logger.info("Creating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact created successfully wi th info: " + contact);
        return contact;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Contact contact, @PathVariable Long id) {
        logger.info("Updating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact updated successfully wi th info: " + contact);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        logger.info("Deleting contact with id: " + id);
        Contact contact = contactService.findByid(id);
        contactService.delete(contact);
        logger.info("Contact deleted successfully");
    }
}
