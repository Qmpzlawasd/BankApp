package Account;

import Card.Card;
import AI.AI;
import Client.Client;
import Exceptions.AccountError;
import Exceptions.CardError;

import java.util.List;

public final class CheckingAccount extends Account {
    private double fees;

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
