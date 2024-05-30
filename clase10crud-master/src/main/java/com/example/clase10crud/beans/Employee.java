package com.example.clase10crud.beans;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String hireDate;
    private String managerId;


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employee_id) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String manager_id) {
        this.managerId = managerId;
    }
}
