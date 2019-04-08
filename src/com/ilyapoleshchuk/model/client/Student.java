package com.ilyapoleshchuk.model.client;

import com.ilyapoleshchuk.model.contract.BankContract;

public class Student extends Client {

    public Student(String firstName, String lastName, BankContract bankContract) {
        super(firstName, lastName, bankContract);
    }
}
