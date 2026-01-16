package com.ankit.module2.dto;


/*
    *DTO stand for Data Transfer Object
    * This is POJO class
*/
public class EmployeDTO {
    private Long id;
    private String Name;
    private int age;
    private double salary;
    private String address;
    private  String deparment;

    public EmployeDTO(Long id ,String name, int age, double salary, String address, String deparment) {
        this.id = id;
        Name = name;
        this.age = age;
        this.salary = salary;
        this.address = address;
        this.deparment = deparment;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }
}
