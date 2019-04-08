package com.ilyapoleshchuk.model.client;

import java.util.UUID;

public class PoorClient extends Client {

    public PoorClient(String firstName, String lastName, UUID bankContractId) {
        super(firstName, lastName, bankContractId);
    }
}
