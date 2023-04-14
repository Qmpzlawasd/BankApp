import Bank.Bank;
import Client.Client;
import Employee.Employee;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Bank asd = Bank.getInstance();
// TEST EMP TABLE
//        Employee emp1 = new Employee("Tava","Andrei", new Date(),"asd@asd.com", "IT", 2398,new Date());
//        Employee emp2 = new Employee("Lefter","Andrei", new Date(),"johnxina@asd.com", "IT", 12345,new Date());
//
//
//        asd.hireSomeone(emp1);
//        asd.hireSomeone(emp2);
//        asd.fireSomeone(emp1);
//        System.out.println(asd.getCurrentEmployees());
        Client lefter  = new Client("lefter" , "adlsk",new Date(2002,4,16) , "sdfsdf@dff.com", 0);
        Client adela  = new Client("adela" , "sadasd",new Date(2002,6, 25) , "sdfsdf@dff.com", 12);



    }
}
