package com.ilyapoleshchuk.model.client;

import com.ilyapoleshchuk.model.common.Person;
import com.ilyapoleshchuk.model.bank.BankAccount;
import com.ilyapoleshchuk.model.bank.BankCard;
import com.ilyapoleshchuk.model.contract.BankContract;

abstract public class Client extends Person {

    private final BankContract bankContract;
    private BankCard bankCard;

    public Client(String firstName, String lastName, BankContract bankContract) {
        super(firstName, lastName);
        this.bankContract = bankContract;
    }

    public BankContract getBankContract() {
        return bankContract;
    }

    public BankAccount getBankAccount() {
        return bankContract.getBankAccount();
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
