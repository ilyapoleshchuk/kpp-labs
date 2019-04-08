package com.ilyapoleshchuk.model.contract;

import com.ilyapoleshchuk.model.bank.Bank;
import com.ilyapoleshchuk.model.bank.BankAccount;
import com.ilyapoleshchuk.model.common.Person;

import java.util.UUID;

public class BankContract {

    private UUID id;
    private final Bank bank;
    private final BankAccount bankAccount;
    private final Person person;

    public BankContract(Bank bank, Person person) {
        this.bank = bank;
        this.person = person;
        this.bankAccount = new BankAccount();
        this.id = UUID.randomUUID();
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Сontract between bank [" + bank.toString() + "] and client [" + person.toString() + "] #" + id;
    }
}
