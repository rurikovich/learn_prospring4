package org.learn.prospring4.chapter16.mvc.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

import static org.springframework.format.annotation.DateTimeFormat.*;

/**
 * Created by User on 21.01.2017.
 */
@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "FIRST_NAМE")
    private String firstName;

    @Column(name = "LAST_NAМE")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    private DateTime birthDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "РНОТО")
    private byte[] photo;

    public Contact() {
    }

    public Contact(long id, String firstName, String lastName, DateTime birthDate, int version) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.version = version;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Transient
    public String getBirthDateString() {
        String birthDateString = "";
        if (birthDate != null) {
            birthDateString = org.joda.time.format.DateTimeFormat
                    .forPattern("yyyy-MM-dd").print(birthDate);
        }
        return birthDateString;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", description='" + description + '\'' +
                '}';
    }
}
