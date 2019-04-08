package com.ilyapoleshchuk.model.client;

import java.util.UUID;

public class RichClient extends Client {

    public RichClient(String firstName, String lastName, UUID bankContractId) {
        super(firstName, lastName, bankContractId);
    }
}
