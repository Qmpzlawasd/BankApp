package model.account;

import model.card.Card;
import model.AI;
import model.Client;
import exceptions.AccountError;
import exceptions.CardError;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class CheckingAccount extends Account {
    private double fees;

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "model.client=" + client +
                ", fees=" + fees +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public void check(double amount, Card card) throws AccountError {
        super.check();
        try {
            card.check(amount + fees);

        } catch (CardError asd) {
            throw new AccountError("Fees cannot be paid.");
        }
    }

    public CheckingAccount(Client client) {
        super(client);
        this.fees = AI.calculateFees(client);
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    protected List<Card> cards;
}
