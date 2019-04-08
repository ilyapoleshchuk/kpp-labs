package com.ilyapoleshchuk.model.client;

import com.ilyapoleshchuk.model.contract.BankContract;

public class PoorClient extends Client {

    public PoorClient(String firstName, String lastName, BankContract bankContract) {
        super(firstName, lastName, bankContract);
    }
}
