package Client;

import Account.Account;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client {
    protected String firstName;
    protected String lastName;
    protected Date birthday;
    protected String email;
    protected int creditScore;
    protected List<Account> accounts;

    public void createAccount(Client asd){}
    public void DeleteAccount(Client asd){}





    public Client(String firstName, String lastName, Date birthday, String email, int creditScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.creditScore = creditScore;
        this.accounts = new ArrayList<>();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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
