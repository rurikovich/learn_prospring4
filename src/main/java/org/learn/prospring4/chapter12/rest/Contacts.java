package org.learn.prospring4.chapter12.rest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 21.01.2017.
 */
public class Contacts implements Serializable {

    private List<Contact> contacts;

    public Contacts() {
    }

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
