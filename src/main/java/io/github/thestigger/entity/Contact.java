package io.github.thestigger.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

/**
 * Contact entity.
 */
@Document(collection = "Contacts")
public class Contact {

    @Id
    private String id;

    @Size(min = 1, message = "Name cannot be empty!")
    private String name;

    @Size(min = 1, message = "Surname cannot be empty!")
    private String surname;
    private String jobPosition;
    private String phoneNumber;
    private String email;
    private String organizationId;
    private boolean director;

    @DBRef
    private Organization company;

    public Contact() {
    }

    public Contact(String name, String surname, String jobPosition, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.jobPosition = jobPosition;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public boolean isDirector() {
        return director;
    }

    public void setDirector(boolean director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organization getCompany() {
        return company;
    }

    public void setCompany(Organization company) {
        this.company = company;
    }
}
