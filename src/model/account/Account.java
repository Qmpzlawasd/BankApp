package model.account;

import model.card.Card;
import model.Client;
import exceptions.AccountError;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public sealed abstract class Account permits CheckingAccount, SavingsAccount {
    protected Client client;
    protected List<Card> cards;
    protected LocalDate dateCreated;
    protected LocalDate dateDeleted = null;

    public List<Card> getCards() {
        return cards;
    }

    public Account(Client client) {
        this.client = client;
        cards = new ArrayList<>();
        this.dateCreated = LocalDate.now();
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
    }

    public void check() throws AccountError {
        if (dateDeleted != null)
            throw new AccountError("The model.account is deleted.");
    }

    public abstract void check(double amount, Card card) throws AccountError;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(LocalDate dateDeleted) {
        this.dateDeleted = dateDeleted;
    }
}
