package com.ilyapoleshchuk.model.client;

import java.util.UUID;

abstract public class Client {

    private UUID id;
    private String firstName;
    private String lastName;

    public Client(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.lastName = lastName;
    }
}
