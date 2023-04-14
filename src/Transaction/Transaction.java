package Transaction;

import Card.Card;

public class Transaction {
    private Card src;
    private Card dest;
    private double amount;
    private String description;

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
