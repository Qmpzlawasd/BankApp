package Account;


import Card.Card;
import Client.Client;
import Exceptions.AccountError;
import Exceptions.CardError;

public class SavingsAccount extends Account {

    protected double withdrawalLimit;
    protected double minimumBalance;


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

    public SavingsAccount(Client client, double withdrawalLimit, double minimumBalance) {
        super(client);
        this.withdrawalLimit = withdrawalLimit;
        this.minimumBalance = minimumBalance;
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
