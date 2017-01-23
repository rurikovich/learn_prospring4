package org.learn.prospring4.chapter12.rest.entities;

import org.joda.time.DateTime;

/**
 * Created by User on 21.01.2017.
 */
public class Contact {
    private long id;
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private int version;

    public Contact() {
    }

    public Contact(long id, String firstName, String lastName, DateTime birthDate, int version) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
