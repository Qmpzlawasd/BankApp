import Bank.Bank;
import Client.Client;
import Employee.Employee;
import Exceptions.AlreadyExists;

import java.util.List;

public final class Service {

    private static Service instance;
    public final static String meniu = "Meniu:\n" +
            "1.Hire someone\n" +
            "2.Fire Someone:\n" +
            "3.Add account\n" +
            "4.Add Card\n" +
            "5.Print extras de cont\n" +
            "6.Print card status\n" +
            "7.MAKE TRANSACTION\n" +
            "8.Add money to account\n" +
            "9.Add Bank\n" +
            "11.List Employees\n" +
            "12.Add Client\n" +
            "10.quit";

    public int addClient(Bank bank, Client asd) {
        try {
            bank.addClient(asd);
        } catch (AlreadyExists e) {
            return 1;
        }
        return 0;
    }

    public void hireEmployee(Bank bank, Employee asd) {
        bank.hireSomeone(asd);
    }

    public void fireEmployee(Bank bank, Employee asd) {
        bank.fireSomeone(asd);
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