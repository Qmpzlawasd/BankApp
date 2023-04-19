package Card;

import Account.Account;
import Exceptions.CardError;
import Exceptions.TransactionError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public sealed abstract class Card permits DebitCard, CreditCard {
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
    public abstract void subtractFunds(double amount) ;
    public abstract void addFunds(double amount);

    private String generateSecureCardNumber() {
        return String.format("%d %d %d %d", Math.round(Math.random() * (9999 - 1000 + 1) + 1000),
                Math.round(Math.random() * (9999 - 1000 + 1) + 1000),
                Math.round(Math.random() * (9999 - 1000 + 1) + 1000),
                Math.round(Math.random() * (9999 - 1000 + 1) + 1000));
    }

    public List<Transaction> getTransactions() {
        return transactions;
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

    private static class Transaction {
        private Card src;
        private Card dest;
        private double amount;
        private String description;

        public Transaction(Card src, Card dest, double amount, String description) {
            this.src = src;
            this.dest = dest;
            this.amount = amount;
            this.description = description;
            completeTransaction(src,dest,amount);
        }

        private void completeTransaction(Card src, Card dest, double amount) {
            src.subtractFunds(amount);
            dest.addFunds(amount);
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "src=" + src +
                    ", dest=" + dest +
                    ", amount=" + amount +
                    ", description='" + description + '\'' +
                    '}';
        }

        public Card getSrc() {
            return src;
        }

        public void setSrc(Card src) {
            this.src = src;
        }

        public Card getDest() {
            return dest;
        }

        public void setDest(Card dest) {
            this.dest = dest;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


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
