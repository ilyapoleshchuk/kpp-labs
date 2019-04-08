package com.ilyapoleshchuk.model.common;

public class Money {

    private double amount;

    public Money(double amount) {
        setAmount(amount);
    }

    public Money() {
        this(0.0);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Money: " + amount;
    }
}
