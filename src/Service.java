import Account.Account;
import Bank.Bank;
import Card.Card;
import Client.Client;
import Employee.Employee;
import Exceptions.AlreadyExists;
import Exceptions.TransactionError;

import java.util.List;

public final class Service {

    private static Service instance;
    public final static String meniu = "Meniu:\n" +
            "1.Hire someone\n" +
            "2.Fire Someone:\n" +
            "3.Add account\n" +
            "4.Add Card\n" +
            "7.MAKE TRANSACTION\n" +
            "8.Add money to account\n" +
            "9.Add Bank\n" +
            "10.quit\n" +
            "11.List Employees\n" +
            "12.Add Client\n" +
            "13.List Clients\n" +
            "14.Get all accounts from Client\n" +
            "15.List all cards from Account\n";

    public int addClient(Bank bank, Client asd) {
        try {
            bank.addClient(asd);
        } catch (AlreadyExists e) {
            return 1;
        }
        return 0;
    }

    public void addAccount(Client client, Account account) {
        client.addAccount(account);
    }

    public void addCard( Account account, Card card) {
        account.addCard(card);
    }

    public void hireEmployee(Bank bank, Employee asd) {
        bank.hireSomeone(asd);
    }

    public void fireEmployee(Bank bank, Employee asd) {
        bank.fireSomeone(asd);
    }

    public void makeTransaction(Card sender, Card receiver, double amount, String message) throws TransactionError {
        sender.makeTransaction(receiver, amount, message);
    }

    public List<Card> getCards(Account account) {
        return account.getCards();
    }

    public List<Account> getAccounts(Client client) {
        return client.getAccounts();
    }

    public List<Employee> getListEmployees(Bank bank) {
        return bank.getCurrentEmployees();
    }

    public List<Client> getListClients(Bank bank) {
        return bank.getClients().stream().toList();
    }


    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}