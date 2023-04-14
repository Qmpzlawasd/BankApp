package Card;

import Account.Account;
import Transaction.Transaction;

import java.util.List;

public class Card  {
    protected Account account;
    protected List<Transaction> transactions;
    protected String expirationDate;

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }

    protected String number;
    protected boolean isVirtual;

}
