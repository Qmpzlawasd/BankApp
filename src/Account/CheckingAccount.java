package Account;

import Card.Card;
import Client.Client;

import java.util.List;

public class CheckingAccount extends Account
{
    protected double fees;

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    protected List<Card> cards;
}
