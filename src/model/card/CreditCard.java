package model.card;

import model.AI;
import model.account.Account;
import exceptions.CardError;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreditCard extends Card {
    private double moneySpent = 0;
    private double creditLimit;

    public CreditCard(Account account) {
        super(account);
        this.creditLimit = AI.calculateCreditLimit(account.getClient());
    }

    @Override
    public void subtractFunds(double amount) {
        moneySpent += amount;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }

    @Override
    public void addFunds(double amount) {
        moneySpent -= amount;
    }

    public void check(double amount) throws CardError {
//        super.check();
        if (moneySpent + amount > creditLimit) {
            throw new CardError("Credit limit hit.");
        }
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

}
