package org.learn.prospring4.chapter12.rest;

import com.google.common.collect.Lists;
import org.learn.prospring4.chapter12.rest.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 22.01.2017.
 */
@Service("springJpaContactService")
@Repository
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Contact findByid(Long id) {
        return contactRepository.findOne(id);
    }

    @Transactional
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }
}
