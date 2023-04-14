package Account;

import Card.Card;
import Client.Client;

import java.util.List;

public class Account {
    protected Client client;
    protected List<Card> cards;
    protected String dateCreated;
    protected String dateDeleted = null;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(String dateDeleted) {
        this.dateDeleted = dateDeleted;
    }
}
