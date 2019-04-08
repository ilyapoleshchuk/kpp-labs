package com.ilyapoleshchuk.model.bank;

import com.ilyapoleshchuk.model.common.Money;

import java.util.UUID;

public class BankAccount {

    private UUID id;
    private final Money money;

    public BankAccount(Money money) {
        this.money = money;
        this.id = UUID.randomUUID();
    }

    public BankAccount() {
        this(new Money());
    }

    public Money getMoney() {
        return money;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bank account #" + id.toString();
    }
}
