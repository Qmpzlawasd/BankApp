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
        employees.add(emp);
        Collections.sort(employees);
    }

    public void fireSomeone(Employee emp) {
        emp.setDateFired(LocalDate.now());
    }


    //TODO: TEST
    public List<Employee> getCurrentEmployees() {
        return employees.stream().filter(currEmp -> currEmp.getDateFired() == null).collect(Collectors.toList());
    }

    public void addClient(Client newClient) throws AlreadyExists{
        if (clients.contains(newClient)) {
            throw  new AlreadyExists("clientul are deja cont la acesta banca");
        }
        newClient.setBank(this);
        clients.add(newClient);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "clients=" + employees +
                '}';
    }
    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

}
