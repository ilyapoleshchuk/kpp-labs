package com.ilyapoleshchuk.model.bank;

import com.ilyapoleshchuk.model.common.Money;

import java.util.UUID;

public class BankCard {

    private final UUID id;
    private final BankAccount bankAccount;

    public BankCard(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.id = UUID.randomUUID();
    }

    public Money getAmount() {
        return bankAccount.getMoney();
    }

    @Override
    public String toString() {
        return "Bank card #" + id.toString() + "of bank account #" + bankAccount.getId().toString();
    }
}
