package com.ilyapoleshchuk.model.client;

import com.ilyapoleshchuk.model.contract.BankContract;

public class RichClient extends Client {

    public RichClient(String firstName, String lastName, BankContract bankContract) {
        super(firstName, lastName, bankContract);
    }
}
