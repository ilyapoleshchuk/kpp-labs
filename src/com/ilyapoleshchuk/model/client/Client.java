package com.ilyapoleshchuk.model.client;

import com.ilyapoleshchuk.model.bank.BankCard;
import com.ilyapoleshchuk.model.common.Person;

import java.util.UUID;

abstract public class Client extends Person {

    private final UUID bankContractId;
    private BankCard bankCard;

    public Client(String firstName, String lastName, UUID bankContractId) {
        super(firstName, lastName);
        this.bankContractId = bankContractId;
    }

    public UUID getBankContractId() {
        return bankContractId;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public boolean hasBankCard() {
        return bankCard != null;
    }

    public void addBankCard(BankCard bankCard) {
        if (this.bankCard != null) {
            throw new IllegalStateException("Client cannot have more than one card");
        }
        this.bankCard = bankCard;
    }

    public void removeBankCard() {
        if (this.bankCard == null) {
            throw new IllegalStateException("Client does not have bank card");
        }
        this.bankCard = null;
    }
}
