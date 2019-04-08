package com.ilyapoleshchuk.model.client;

import java.util.UUID;

public class Student extends Client {

    public Student(String firstName, String lastName, UUID bankContractId) {
        super(firstName, lastName, bankContractId);
    }
}
