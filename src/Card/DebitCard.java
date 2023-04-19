package Card;

import AI.AI;
import Account.Account;
import Exceptions.CardError;

public final class DebitCard extends Card {
    private double balance = 0;
    private double overdraftLimit; // max cat poti sa scoto in plus


    public DebitCard(Account account) {
        super(account);
        this.overdraftLimit = AI.calculateOverdraftLimit(account.getClient());
    }

    @Override
    public void subtractFunds(double amount) {
        balance -= amount;
    }

    @Override
    public void addFunds(double amount) {
        balance += amount;
    }

    public void check(double amount) throws CardError {
//        super.check();
        if (balance + overdraftLimit < amount) {
            throw new CardError("Not enough funds");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

}
