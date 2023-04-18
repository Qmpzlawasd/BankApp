package Card;

import Account.Account;
import Exceptions.CardError;
import Exceptions.TransactionError;
import Transaction.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    protected String number;
    protected LocalDate expirationDate;
    protected LocalDate disabledDate = null;
    protected Account account;
    protected List<Transaction> transactions;

    protected void check() throws CardError {
        if (disabledDate != null || LocalDate.now().isAfter(expirationDate)) {
            throw new CardError("the card is inactive");
        }
    }

    public abstract void check(double amount) throws CardError;

    private String generateSecureCardNumber() {
        return String.format("%d %d %d %d", Math.round(Math.random() * (9999 - 1000 + 1) + 1000),
                Math.round(Math.random() * (9999 - 1000 + 1) + 1000),
                Math.round(Math.random() * (9999 - 1000 + 1) + 1000),
                Math.round(Math.random() * (9999 - 1000 + 1) + 1000));
    }

    public Card(Account account) {
        this.number = generateSecureCardNumber();
        this.expirationDate = LocalDate.now().plusYears(2);
        this.account = account;
        transactions = new ArrayList<>();
    }

    public void makeTransaction(Card sendTo, double amount, String description) throws TransactionError {
        if (amount <= 0) {
            throw new TransactionError("Invalid amount");
        }
        sendTo.check();
        sendTo.account.check();
        this.check(); //???
        this.account.check(amount, this);
        this.check(amount);

        if (description.isEmpty()) {
            description = "Transfer BlancaPay";
        }

        Transaction transaction = new Transaction(this, sendTo, amount, description);
        transactions.add(transaction);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(LocalDate disabledDate) {
        this.disabledDate = disabledDate;
    }

}
