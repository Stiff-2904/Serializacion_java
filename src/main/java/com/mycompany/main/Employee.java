package com.mycompany.main;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String lastName;
    private int ID;
    private int age;
    int idDeparmentEm;

    public Employee(String name, String lastName, int ID, int age) {
        this.name = name;
        this.lastName = lastName;
        this.ID = ID;
        this.age = age;
        this.idDeparmentEm = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdDeparment() {
        return idDeparmentEm;
    }

    public void setIdDeparment(int idDeparment) {
        this.idDeparmentEm = idDeparment;
    }

    public String toString() {
        String message = "{El empleado " + name + " " + lastName + " con " + age + " años y la cedula es " + ID + "}\n";

        return message;
    }
}
