package Employee;

import java.util.Date;

public class Employee implements Comparable<Employee> {
    protected String firstName;
    protected String lastName;
    protected Date birthday;
    protected String email;
    protected String department;
    protected int salary;
    protected Date dateHired;
    protected Date dateFired = null;


    public Employee(String firstName, String lastName, Date birthday, String email, String department, int salary,
                    Date dateHired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.dateHired= dateHired;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", dateHired=" + dateHired +
                '}';
    }

    @Override
    public int compareTo(Employee anotherInstance) {
        if (this.dateHired.equals(anotherInstance.dateHired)) return 0;
        if (this.dateHired.after(anotherInstance.dateHired)) return 1;
        return -1;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public Date getDateFired() {
        return dateFired;
    }

    public void setDateFired(Date dateFired) {
        this.dateFired = dateFired;
    }


}
