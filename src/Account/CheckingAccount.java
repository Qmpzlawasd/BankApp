package Account;

import Card.Card;
import Client.Client;
import Exceptions.AccountError;
import Exceptions.CardError;

import java.util.List;

public class CheckingAccount extends Account {
    protected double fees;

    public void check(double amount, Card card) throws AccountError {
        super.check();
        try {
            card.check(amount + fees);

        } catch (CardError asd) {
            throw new AccountError("Fees cannot be paid.");
        }
    }

    public CheckingAccount(Client client, double fees) {
        super(client);
        this.fees = fees;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    protected List<Card> cards;
}
