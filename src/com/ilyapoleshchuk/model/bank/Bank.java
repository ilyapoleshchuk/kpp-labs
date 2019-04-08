package com.ilyapoleshchuk.model.bank;

public class Bank {

    private final String name;
    private final BankServer bankServer;

    public Bank(String name) {
        this.name = name;
        this.bankServer = new BankServer(this);
    }

    public BankServer getBankServer() {
        return bankServer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank: " + name;
    }
}
