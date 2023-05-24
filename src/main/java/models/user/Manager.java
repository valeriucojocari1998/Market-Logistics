package models.user;

import enums.ProductType;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Manager extends User {
    private Integer salary;
    public Manager(String name, Integer age, Integer salary) {
        super(name, age);
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }


    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        String nameValue = "Name: " + getName();
        String ageValue = "Age: " + getAge();
        String salaryValue = "Salary: " + getSalary();

        return String.join(System.lineSeparator(), "Manager Object", nameValue, ageValue, salaryValue);
    }

}
