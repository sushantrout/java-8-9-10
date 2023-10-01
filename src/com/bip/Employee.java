package com.bip;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private String position;
    private String department;
    private Date hireDate;

    // Constructor
    public Employee(int id, String name, double salary, String position, String department, Date hireDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.hireDate = hireDate;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public Date getHireDate() {
        return hireDate;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    // Method to give an annual raise
    public void giveRaise(double percentage) {
        double raiseAmount = salary * percentage / 100;
        salary += raiseAmount;
    }

    // Override toString method to represent the object as a string

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(position, employee.position) && Objects.equals(department, employee.department) && Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, position, department, hireDate);
    }
}

