package com.ilyapoleshchuk.model.cashmachine;

import com.ilyapoleshchuk.model.bank.BankCard;
import com.ilyapoleshchuk.model.bank.BankServer;
import com.ilyapoleshchuk.model.common.Money;

public class CashMachine {

    public final static int ATTEMPTS = 3;

    private final BankServer bankServer;
    private BankCard bankCard = null;
    private int attemptsLeft = ATTEMPTS;

    public CashMachine(BankServer bankServer) {
        this.bankServer = bankServer;
    }

    public boolean inputPassword(BankCard bankCard, String password) {
        boolean successful = bankServer.validate(bankCard, password);
        if (successful) {
            attemptsLeft = ATTEMPTS;
            return true;
        } else if (attemptsLeft > 1) {
            attemptsLeft--;
            return false;
        } else {
            throw new IllegalStateException("You missed " + ATTEMPTS + " attempts");
        }
    }

    public void addMoney(Money money) {
        if (bankCard == null) {
            throw new IllegalStateException("Card is not validated yet");
        }
        bankServer.topUpAccount(bankCard, money);
    }

    public Money cashOut(double percentage) {
        if (bankCard == null) {
            throw new IllegalStateException("Card is not validated yet");
        }
        return bankServer.cashOut(bankCard, percentage);
    }
}
