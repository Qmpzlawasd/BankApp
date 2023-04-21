package Account;


import AI.AI;
import Card.Card;
import Client.Client;
import Exceptions.AccountError;
import Exceptions.CardError;

public final class SavingsAccount extends Account {

    private double withdrawalLimit;
    private double minimumBalance;


    @Override
    public String toString() {
        return "SavingsAccount{" +
                "client=" + client +
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
