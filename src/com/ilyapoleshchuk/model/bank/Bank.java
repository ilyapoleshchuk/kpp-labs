package com.ilyapoleshchuk.model.bank;

public class Bank {

    private final String name;

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank: " + name;
    }
}
