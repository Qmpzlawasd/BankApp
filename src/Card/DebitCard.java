package Card;

import Account.Account;
import Exceptions.CardError;

public class DebitCard extends Card {
    private double balance = 0;

    public DebitCard(Account account, double overdraftLimit) {
        super(account);
        this.overdraftLimit = overdraftLimit;
    }

    public void check(double amount) throws CardError {
//        super.check();
        if (balance < amount){
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

    private double overdraftLimit; // max cat poti sa scoto
}
