package org.learn.prospring4.chapter12.rest;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by User on 22.01.2017.
 */
public interface ContactRepository extends CrudRepository<Contact,Long> {
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
