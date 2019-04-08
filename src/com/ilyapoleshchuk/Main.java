package com.ilyapoleshchuk;

import com.ilyapoleshchuk.model.bank.Bank;
import com.ilyapoleshchuk.model.bank.BankCard;
import com.ilyapoleshchuk.model.bank.EnvelopeWithBankCardAndPassword;
import com.ilyapoleshchuk.model.cashmachine.CashMachine;
import com.ilyapoleshchuk.model.client.Client;
import com.ilyapoleshchuk.model.client.ClientStatus;
import com.ilyapoleshchuk.model.common.Money;
import com.ilyapoleshchuk.model.common.Person;

public class Main {

    public static void main(String[] args) {

        //a bank and cash machine appeared

        Bank bank = new Bank("Swiss bank");
        CashMachine cashMachine = bank.releaseCashMachine();

        log("Create bank with cash machine - " + bank.toString());

        //several people were born

        Person poorMan = new Person("Vasya", "Pupkin");
        Person richWoman = new Person("Olya", "Sidorova");
        Person jushStudent = new Person("Ivan", "Ivanov");

        log("Created person - " + poorMan.toString());
        log("Created person - " + richWoman.toString());
        log("Created person - " + jushStudent.toString());

        //this guys go to bank and register bank account

        poorMan = bank.registerPersonAsClient(poorMan, ClientStatus.POOR);
        richWoman = bank.registerPersonAsClient(richWoman, ClientStatus.RICH);
        jushStudent = bank.registerPersonAsClient(jushStudent, ClientStatus.STUDENT);

        //now they are clients

        log("Is poorMan client? - " + (poorMan instanceof Client));
        log("Is richWoman client? - " + (richWoman instanceof Client));
        log("Is justStudent client? - " + (jushStudent instanceof Client));

        Client poorManClient = (Client) poorMan;
        Client richWomanClient = (Client) richWoman;
        Client justStudentClient = (Client) jushStudent;

        //some of them want bank card, so they go to bank and get it in envelope

        EnvelopeWithBankCardAndPassword envelope = bank.releaseBankCard(justStudentClient.getBankContractId());

        //open envelope and take contents

        envelope.open();

        BankCard bankCard = envelope.getBankCard();
        String password = envelope.getPassword();

        log(bankCard.toString());
        log("Password for bank card: " + password);

        //put bank card in pocket

        justStudentClient.addBankCard(bankCard);

        //this client with new bank card decided to try it, so he will use cash machine

        //trying to input password to validate bank card

        String wrongPassword = password.concat("123");

        boolean isTryWithWrongPasswordSuccessful =
            cashMachine.inputPassword(justStudentClient.getBankCard(), wrongPassword);
        log("Is try with wrong password successful? - " + isTryWithWrongPasswordSuccessful);

        boolean isTryWithRightPasswordSuccessful =
            cashMachine.inputPassword(justStudentClient.getBankCard(), password);
        log("Is try with right password successful? - " + isTryWithRightPasswordSuccessful);

        //add some cash

        Money cash = new Money(188.9);
        log("Cash: " + cash.toString());
        cashMachine.addMoney(cash);

        //then take back some portion to test

        Money money = cashMachine.cashOut(/* 11.7% */11.7);
        log("Money from cash machine: " + money.toString());
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
