import database.LogDatabase;
import model.account.Account;
import model.Bank;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.card.Card;
import model.Client;
import model.Employee;
import exceptions.AlreadyExists;
import exceptions.TransactionError;
import model.card.CreditCard;
import model.card.DebitCard;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public final class Service extends LogDatabase {

    private static Service instance;


    public final static String meniu = "Meniu:\n" +
            "1.Hire someone\n" +
            "2.Fire Someone:\n" +
            "3.Add model.account\n" +
            "4.Add Card\n" +
            "7.MAKE TRANSACTION\n" +
            "8.Add money to model.account\n" +
            "9.Add Bank\n" +
            "10.quit\n" +
            "11.List Employees\n" +
            "12.Add Client\n" +
            "13.List Clients\n" +
            "14.Get all accounts from Client\n" +
            "15.List all cards from Account\n" +
            "16.Update client email\n";
    //CREATE
    public CheckingAccount makeCheckingAccount(Client client) {
        CheckingAccount asd = new CheckingAccount(client);
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Account(client,dateCreated,isChecking,fees," +
                    "withdrawalLimit,minimumBalance) values (?,?,?,?,?,?);");

            stmt.setString(1, client.getEmail());
            stmt.setString(2, asd.getDateCreated().toString());
            stmt.setInt(3, 1);
            stmt.setDouble(4, asd.getFees());
            stmt.setObject(5, null);
            stmt.setObject(6, null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return asd;

    }

    public SavingsAccount makeSavingsAccount(Client client) {
        SavingsAccount asd = new SavingsAccount(client);

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Account(client,dateCreated,isChecking,fees," +
                    "withdrawalLimit,minimumBalance) values (?,?,?,?,?,?);");

            stmt.setString(1, client.getEmail());
            stmt.setString(2, asd.getDateCreated().toString());
            stmt.setInt(3, 0);
            stmt.setObject(4, null);
            stmt.setDouble(5, asd.getWithdrawalLimit());
            stmt.setDouble(6, asd.getMinimumBalance());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return asd;
    }

    public CreditCard makeCreditCard(Account account) {
        CreditCard asd = new CreditCard(account);
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Card(account,number,expirationDate,isCredit,creditLimit,overdraftLimit)" +
                    "values (?,?,?,?,?,?);");

            stmt.setInt(1, account.getClient().getAccounts().indexOf(account) + 1);
            stmt.setString(2, asd.getNumber());
            stmt.setString(3, asd.getExpirationDate().toString());
            stmt.setInt(4, 1);
            stmt.setDouble(5, asd.getCreditLimit());
            stmt.setObject(6, null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return asd;
    }

    public DebitCard makeDebitCard(Account account) {
        DebitCard asd = new DebitCard(account);

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Card(account,number,expirationDate,isCredit,creditLimit,overdraftLimit)" +
                    "values (?,?,?,?,?,?);");

            stmt.setInt(1, account.getClient().getAccounts().indexOf(account) + 1);
            stmt.setString(2, asd.getNumber());
            stmt.setString(3, asd.getExpirationDate().toString());
            stmt.setInt(4, 0);
            stmt.setObject(5, null);
            stmt.setDouble(6, asd.getOverdraftLimit());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return asd;
    }

    public Bank makeBank(String locatie) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Bank(locatie) values (?);");
            stmt.setString(1, locatie);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Bank(locatie);
    }

    public Client makeClient(String firstName, String lastName, LocalDate birthday, String email, int bankIndex) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Client(firstName,lastName,birthday,email,dateOpened,bank)" +
                    "values (?,?,?,?,?,?);");

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, birthday.toString());
            stmt.setString(4, email);
            stmt.setString(5, LocalDate.now().toString());
            stmt.setInt(6, bankIndex);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Client(firstName, lastName, birthday, email);
    }

    public Employee makeEmployee(String firstName, String lastName, LocalDate birthday, String email, String department,
                                 int salary, int bankIndex) {
        Employee asd = new Employee(firstName, lastName, birthday, email, department, salary);

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Employee(firstName,lastName,birthday,email," +
                    "department,salary,bank,dateHired)" +
                    "values (?,?,?,?,?,?,?,?);");

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, birthday.toString());
            stmt.setString(4, email);
            stmt.setString(5, department);
            stmt.setInt(6, salary);
            stmt.setInt(7, bankIndex);
            stmt.setString(8, asd.getDateHired().toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return asd;
    }


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

    public void addCard(Account account, Card card) {
        account.addCard(card);
    }

    public void hireEmployee(Bank bank, Employee asd) {
        bank.hireSomeone(asd);
    }

    public void makeTransaction(Card sender, Card receiver, double amount, String message) throws TransactionError {
        sender.makeTransaction(receiver, amount, message);
    }

    //DELETE
    public void fireEmployee(Bank bank, Employee employee) {
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from Employee where Employee.email = ?;");
            stmt.setString(1, employee.getEmail());


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        bank.fireSomeone(employee);

    }

    //READ
    public List<Card> getCards(Account account) {
        return account.getCards();
    }

    public List<Account> getAccounts(Client client) { // TODO: Do this
        return client.getAccounts();
    }

    public List<Employee> getListEmployees(Bank bank) { // TODO: Do this
        return bank.getCurrentEmployees();
    }

    public List<Client> getListClients(Bank bank) { // TODO: Do this

        return bank.getClients();
    }
//    public List<Account> getAccounts(Client client) { // TODO: Do this
//
//        return client.getAccounts();
//    }
//
//    public List<Employee> getListEmployees(Bank bank) { // TODO: Do this
//        return bank.getCurrentEmployees();
//    }
//
//    public List<Client> getListClients(Bank bank) { // TODO: Do this
//        return bank.getClients();
//    }

    //UPDATE
    void updateClientEmail(Client client, String newEmail) {
        try {
            PreparedStatement stmt = conn.prepareStatement("update Client set email = ? where Client.email = ?;\n");

            stmt.setString(1, newEmail);
            stmt.setString(2, client.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        client.setEmail(newEmail);

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