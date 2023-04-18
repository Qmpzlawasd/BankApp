package Account;

import Card.Card;
import Client.Client;
import Exceptions.AccountError;
import Exceptions.AlreadyExists;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected Client client;
    protected List<Card> cards;
    protected LocalDate dateCreated;
    protected LocalDate dateDeleted = null;


    public Account(Client client) {
        this.client = client;
        cards = new ArrayList<>();
        this.dateCreated = LocalDate.now();
    }

    public void addCard(Card newCard) throws AlreadyExists {
        if (cards.stream().anyMatch(card -> card.getNumber().equals(newCard.getNumber()))) {
            throw new AlreadyExists("cardul deja exista in cont");
        }
        cards.add(newCard);
    }

    public void check() throws AccountError {
        if (dateDeleted != null)
            throw new AccountError("The account is deleted.");
    }

    public abstract void check(double amount,Card card) throws AccountError;

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
