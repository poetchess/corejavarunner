//'package' statement is used to place classes inside a package.
//If there's no 'package' statement, classes in souce file belong to the
//default package, which has no package name.
//Source files should be placed into a subdirectory that matches the full
//package name.
package com.github.poetchess.packagetest;

import java.time.*;

public class Employee {

    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
