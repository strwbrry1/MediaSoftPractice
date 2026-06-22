package ru.ulstu;

public class Main {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount("Ivan");
        BankAccount accountB = new BankAccount("Kirill");
        System.out.println(accountA);

        accountA.withdraw(1); // ошибочное действие

        accountA.deposit(100);
        System.out.println(accountA.getBalance());

        accountA.transfer(accountB, 101); // ошибочное действие

        accountA.transfer(accountB, 100);
        System.out.println(accountA.getBalance());

    }
}