package com.ilyapoleshchuk.model.bank;

import com.ilyapoleshchuk.model.cashmachine.CashMachine;
import com.ilyapoleshchuk.model.client.Client;
import com.ilyapoleshchuk.model.client.ClientStatus;
import com.ilyapoleshchuk.model.common.Person;

import java.util.UUID;

public class Bank {

    private final String name;
    private final BankServer bankServer;

    public Bank(String name) {
        this.name = name;
        this.bankServer = new BankServer(this);
    }

    public CashMachine releaseCashMachine() {
        return new CashMachine(bankServer);
    }

    public Client registerPersonAsClient(Person person, ClientStatus clientStatus) {
        return bankServer.registerPersonAsClient(person, clientStatus);
    }

    public void terminateContract(UUID bankContractId) {
        bankServer.terminateContract(bankContractId);
    }

    public EnvelopeWithBankCardAndPassword releaseBankCard(UUID bankContractId) {
        return bankServer.createBankCard(bankContractId);
    }

    public void invalidateBankCard(BankCard bankCard) {
        bankServer.destroyBankCard(bankCard);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank: " + name;
    }
}
