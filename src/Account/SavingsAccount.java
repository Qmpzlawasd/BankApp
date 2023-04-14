package Account;

import Card.DebitCard;

import java.util.List;

public class SavingsAccount extends Account {
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

    protected double withdrawalLimit;
    protected double minimumBalance;
    protected List<DebitCard> cards;
}
