package ru.ulstu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private String ownerName;

    private int balance;

    private LocalDateTime openingDate;

    private boolean isBlocked;

    private String accountNumber;

    public BankAccount(String ownerName) {
        Random r = new Random();
        this.ownerName = ownerName;
        this.balance = 0;
        this.openingDate = LocalDateTime.now();
        this.isBlocked = false;
        this.accountNumber = String.format("%08d",r.nextInt(1, 100_000_000));
    }

    public boolean deposit(int amount) {
        try {
            this.balance += amount;
            return true;
        }
        catch (Exception ex) {
            System.out.printf("Ошибка при выполнении операции: %s%n", ex.getMessage());
            return false;
        }
    }

    public boolean withdraw(int amount) {
        try {
            if (amount > this.balance) {
                throw new IllegalArgumentException("Попытка снять больше денег, чем есть на счёте");
            }
            this.balance -= amount;
            return true;
        }
        catch (Exception ex) {
            System.out.printf("Ошибка при выполнении операции: %s%n", ex.getMessage());
            return false;
        }
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        try {
            this.withdraw(amount);
            otherAccount.deposit(amount);
            return true;
        }
        catch (Exception ex) {
            System.out.printf("Ошибка при выполнении операции: %s%n", ex.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Номер счёта: %s, имя: %s; баланс: %d; счёт открыт: %s; счет заблокирован: %s%n",
                this.accountNumber,
                this.ownerName,
                this.balance,
                this.openingDate.format(DateTimeFormatter.ISO_DATE),
                this.isBlocked);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount account = (BankAccount) o;
        return balance == account.balance
                && isBlocked == account.isBlocked
                && Objects.equals(ownerName, account.ownerName)
                && Objects.equals(openingDate, account.openingDate)
                && Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName, balance, openingDate, isBlocked, accountNumber);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
