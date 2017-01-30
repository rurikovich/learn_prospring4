package org.learn.prospring4.chapter16.mvc;

import org.learn.prospring4.chapter16.mvc.entities.Contact;

import java.util.List;

/**
 * Created by rurik on 25.01.2017.
 */
public interface ContactService {
    List<Contact> findAll();

    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

    Contact findByid(Long id);

    Contact save(Contact contact);

    void delete(Contact contact);

}
