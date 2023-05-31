package Bank;

import Employee.Employee;
import Client.Client;
import Exceptions.AlreadyExists;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Bank {

    private String locatie;
    private final List<Employee> employees;  // soreted
    private final Set<Client> clients;

    public Bank(String locatie) {
        this.locatie = locatie;
        employees = new ArrayList<>();
        clients = new HashSet<>();
    }

    public void hireSomeone(Employee emp) {
        emp.setBank(this);
        employees.add(emp);
        Collections.sort(employees);
    }

    public void fireSomeone(Employee emp) {
        emp.setDateFired(LocalDate.now());
    }

    public List<Employee> getCurrentEmployees() {
        return employees.stream().filter(currEmp -> currEmp.getDateFired() == null).collect(Collectors.toList());
    }

    public void addClient(Client newClient) throws AlreadyExists {
        if (clients.stream().anyMatch(cli -> cli.getLast_name().equals(newClient.getLast_name()) && cli.getFirst_name().equals(newClient.getFirst_name()))) {
            throw new AlreadyExists("The client is already registered at this bank.");
        }
        newClient.setBank(this);
        clients.add(newClient);
    }


    public String getLocatie() {
        return locatie;
    }


    public Set<Client> getClients() {
        return clients;
    }
    @Override
    public String toString() {
        return "Bank{" +
                "locatie='" + locatie + '\'' +
                '}';
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

}
