package model.account;


import model.AI;
import model.card.Card;
import model.Client;
import exceptions.AccountError;
import exceptions.CardError;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class SavingsAccount extends Account {

    private double withdrawalLimit;
    private double minimumBalance;


    @Override
    public String toString() {
        return "SavingsAccount{" +
                "model.client=" + client +
                ", withdrawalLimit=" + withdrawalLimit +
                ", minimumBalance=" + minimumBalance +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public void check(double amount, Card card) throws AccountError {
        super.check();
        if (withdrawalLimit < amount) {
            throw new AccountError("Withdrawal limit hit.");
        }
        try {
            card.check(amount + minimumBalance);

        } catch (CardError asd) {

            throw new AccountError("Minimum balance limit hit.");
        }
    }

    public SavingsAccount(Client client) {
        super(client);
        this.withdrawalLimit = AI.calculateWithdrawalLimit(client);
        this.minimumBalance = AI.calculateMinimumBalance(client);

    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }


}
