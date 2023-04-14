package Bank;

import Employee.Employee;
import Client.Client;

import java.util.*;

public class Bank {
    static private Bank instance;
    private final List<Employee> employees;  // soreted
    private final Set<Client> clients;

    private Bank() {
        employees = new ArrayList<Employee>();
        clients = new HashSet<Client>();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void hireSomeone(Employee emp) {
        employees.add(emp);
        Collections.sort(employees);
    }

    public void fireSomeone(Employee emp) {
        emp.setDateFired(new Date());
    }

    public List<Employee> getCurrentEmployees() {
        List<Employee> asd = new ArrayList<>();
        for (Employee currEmp : employees) {
            if (currEmp.getDateFired() == null)
                asd.add(currEmp);
        }
        return asd;
    }

    public void addClient(Client asd) { // throws
        if (clients.contains(asd)) {
            //throw "Clientul are deja cont"
            return;
        }
        clients.add(asd);
    }
    @Override
    public String toString() {
        return "Bank{" +
                "clients=" + employees +
                '}';
    }
}
