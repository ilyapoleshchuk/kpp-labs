package com.ilyapoleshchuk.model.bank;

public class EnvelopeWithBankCardAndPassword {

    private final BankCard bankCard;
    private final String password;
    private boolean isOpened;

    public EnvelopeWithBankCardAndPassword(BankCard bankCard, String password) {
        this.bankCard = bankCard;
        this.password = password;
        isOpened = false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        isOpened = true;
        System.out.println("Some peaceful sound...");
    }

    public BankCard getBankCard() {
        if (!isOpened) {
            throw new IllegalStateException("You must open envelope first");
        }
        return bankCard;
    }

    public String getPassword() {
        if (!isOpened) {
            throw new IllegalStateException("You must open envelope first");
        }
        return password;
    }
}
