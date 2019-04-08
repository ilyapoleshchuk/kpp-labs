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

    public void addMoney(Money money) {
        double current = this.money.getAmount();
        this.money.setAmount(current + money.getAmount());
    }

    public void subtractMoney(Money money) {
        double current = this.money.getAmount();
        if (current < money.getAmount()) {
            throw new IllegalStateException("There is no such amount");
        }
        money.setAmount(current - money.getAmount());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bank account #" + id.toString();
    }
}
