package com.ilyapoleshchuk.model.bank;

import com.ilyapoleshchuk.model.client.*;
import com.ilyapoleshchuk.model.common.Money;
import com.ilyapoleshchuk.model.common.Person;
import com.ilyapoleshchuk.model.contract.BankContract;

import java.util.*;

public class BankServer {

    private final Bank bank;
    private final Set<BankContract> contracts;
    private final Map<BankCard, BankAccount> cardsWithAccounts;
    private final Map<BankCard, String> passwords;

    public BankServer(Bank bank) {
        this.bank = bank;
        this.contracts = new HashSet<>();
        this.cardsWithAccounts = new HashMap<>();
        this.passwords = new HashMap<>();
    }

    public Client registerPersonAsClient(Person person, ClientStatus status) {
        if (person == null || status == null) {
            throw new NullPointerException();
        }
        BankContract newContract = new BankContract(bank, person);
        boolean wasAdded = contracts.add(newContract);
        if (!wasAdded) {
            throw new IllegalStateException("Contract between this person and bank already exists");
        }
        Client newClient = null;
        switch (status) {
            case RICH:
                newClient = new RichClient(person.getFirstName(), person.getLastName(), newContract.getId());
            case POOR:
                newClient = new PoorClient(person.getFirstName(), person.getLastName(), newContract.getId());
            case STUDENT:
                newClient = new Student(person.getFirstName(), person.getLastName(), newContract.getId());
        }
        return newClient;
    }

    public void terminateContract(UUID bankContractId) {
        BankContract bankContract = findContractById(bankContractId);
        boolean wasRemoved = contracts.remove(bankContract);
        if (!wasRemoved) {
            throw new NoSuchElementException("Such contract does not exist");
        }
    }

    public void topUpAccount(BankCard bankCard, Money money) {
        if (!cardsWithAccounts.containsKey(bankCard)) {
            throw new NoSuchElementException("Invalid bank card");
        }
        BankAccount bankAccount = cardsWithAccounts.get(bankCard);
        bankAccount.addMoney(money);
        cardsWithAccounts.put(bankCard, bankAccount);
    }

    public Money cashOut(BankCard bankCard, double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Invalid percentage");
        }
        if (!cardsWithAccounts.containsKey(bankCard)) {
            throw new NoSuchElementException("Invalid bank card");
        }
        BankAccount bankAccount = cardsWithAccounts.get(bankCard);
        Money currentMoney = bankAccount.getMoney();
        Money money = new Money(currentMoney.getAmount() * (100 - percentage) / 100);
        bankAccount.subtractMoney(money);
        return money;
    }

    public EnvelopeWithBankCardAndPassword createBankCard(UUID bankContractId) {
        BankCard bankCard = createBankCardWithoutPassword(bankContractId);
        String newPassword = generatePassword();
        passwords.put(bankCard, newPassword);
        return new EnvelopeWithBankCardAndPassword(bankCard, newPassword);
    }

    private BankCard createBankCardWithoutPassword(UUID bankContractId) {
        BankContract bankContract = findContractById(bankContractId);
        BankAccount bankAccount = bankContract.getBankAccount();
        if (cardsWithAccounts.containsValue(bankAccount)) {
            throw new IllegalStateException("Bank card has been already created");
        }
        BankCard bankCard = new BankCard(bankAccount);
        cardsWithAccounts.put(bankCard, bankAccount);
        return bankCard;
    }

    public void destroyBankCard(BankCard bankCard) {
        if (cardsWithAccounts.containsKey(bankCard)) {
            throw new IllegalStateException("No bank card with such id exists");
        }
        cardsWithAccounts.remove(bankCard);
    }

    public boolean validate(BankCard bankCard, String password) {
        if (!cardsWithAccounts.containsKey(bankCard)) {
            throw new IllegalStateException("Invalid bank card");
        }
        return passwords.get(bankCard).equals(password);
    }

    private String generatePassword() {
        Random random = new Random();
        int randomInt = random.nextInt((9999 - 1000) + 1) + 1000;
        return Integer.toString(randomInt);
    }

    private BankContract findContractById(UUID id) {
        Optional<BankContract> optionalBankContract = contracts.stream()
            .filter(it -> it.getId().equals(id))
            .findFirst();
        if (!optionalBankContract.isPresent()) {
            throw new NoSuchElementException("No bank contract by such id exists");
        } else return optionalBankContract.get();
    }
}
