package com.bip;

import com.bip.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProvider {
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            employees.add(new Employee(1, "John Doe", 60000.0, "Software Engineer", "Engineering", dateFormat.parse("2021-01-15")));
            employees.add(new Employee(2, "Jane Smith", 75000.0, "Project Manager", "Management", dateFormat.parse("2020-03-20")));
            employees.add(new Employee(3, "Robert Johnson", 55000.0, "Quality Analyst", "Quality Assurance", dateFormat.parse("2019-07-10")));
            employees.add(new Employee(4, "Emily Wilson", 70000.0, "Data Scientist", "Research", dateFormat.parse("2018-11-05")));
            employees.add(new Employee(5, "Michael Brown", 65000.0, "Marketing Manager", "Marketing", dateFormat.parse("2019-09-15")));
            employees.add(new Employee(6, "Linda Davis", 72000.0, "Sales Manager", "Sales", dateFormat.parse("2017-05-10")));
            employees.add(new Employee(7, "Daniel White", 58000.0, "HR Specialist", "Human Resources", dateFormat.parse("2020-02-01")));
            employees.add(new Employee(8, "Sarah Hall", 63000.0, "Accountant", "Finance", dateFormat.parse("2019-04-30")));
            employees.add(new Employee(9, "James Lee", 68000.0, "Product Manager", "Product Management", dateFormat.parse("2018-08-20")));
            employees.add(new Employee(10, "Karen Clark", 59000.0, "Customer Support", "Customer Service", dateFormat.parse("2021-06-12")));

            employees.add(new Employee(11, "Samuel Adams", 62000.0, "Software Developer", "Engineering", dateFormat.parse("2020-10-25")));
            employees.add(new Employee(12, "Olivia Turner", 72000.0, "Project Lead", "Management", dateFormat.parse("2019-03-18")));
            employees.add(new Employee(13, "Charles Baker", 54000.0, "Quality Control", "Quality Assurance", dateFormat.parse("2020-07-01")));
            employees.add(new Employee(14, "Grace Mitchell", 73000.0, "Data Analyst", "Research", dateFormat.parse("2019-05-30")));
            employees.add(new Employee(15, "William Walker", 68000.0, "Marketing Specialist", "Marketing", dateFormat.parse("2018-04-10")));
            employees.add(new Employee(16, "Ava Nelson", 75000.0, "Sales Executive", "Sales", dateFormat.parse("2017-06-15")));
            employees.add(new Employee(17, "Joseph Parker", 60000.0, "HR Manager", "Human Resources", dateFormat.parse("2019-02-05")));
            employees.add(new Employee(18, "Mia Carter", 66000.0, "Financial Analyst", "Finance", dateFormat.parse("2021-03-22")));
            employees.add(new Employee(19, "Ethan Foster", 71000.0, "Product Designer", "Product Management", dateFormat.parse("2018-09-12")));
            employees.add(new Employee(20, "Chloe Murphy", 57000.0, "Customer Relations", "Customer Service", dateFormat.parse("2020-11-19")));
            employees.add(new Employee(20, "Chloe Murphy", 57000.0, "Customer Relations", "Customer Service", dateFormat.parse("2020-11-19")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
