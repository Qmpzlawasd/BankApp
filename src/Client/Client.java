package Client;

import Account.Account;
import Bank.Bank;

import java.time.LocalDate;
import java.util.List;

public class Client {
    protected String firstName;
    protected String lastName;
    protected LocalDate birthday;
    protected String email;
    protected int creditScore = 0;
    protected LocalDate dateOpened;
    protected List<Account> accounts;
    private Bank bank;

    public void addAccount(Account newAcc) {accounts.add(newAcc);}

    public void deleteAccount(Account asd) {
        accounts.remove(asd);
    }

    public Client(String firstName, String lastName, LocalDate birthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.dateOpened = LocalDate.now();
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String firstName) {
        this.firstName = firstName;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditscore(int creditScore) {
        this.creditScore = creditScore;
    }

}
