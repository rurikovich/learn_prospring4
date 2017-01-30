package org.learn.prospring4.chapter16.mvc;

import org.learn.prospring4.chapter16.mvc.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 22.01.2017.
 */

@Transactional
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
