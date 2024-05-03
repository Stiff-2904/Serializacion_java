package com.mycompany.main;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String lastName;
    private int ID;
    private int age;
    int idDeparment;

    public Employee(String name, String lastName, int ID, int age, int idDeparment) {
        this.name = name;
        this.lastName = lastName;
        this.ID = ID;
        this.age = age;
        this.idDeparment = idDeparment;
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
        return idDeparment;
    }

    public void setIdDeparment(int idDeparment) {
        this.idDeparment = idDeparment;
    }

    public String toString() {
        String message = "\nEl empleado se llama " + name + " " + lastName + " con " + age + " años + y la cedula es " + ID;

        return message;
    }

    public String messageDepart(Department deparmentSelected) {
        String message = "El empleado se llama " + name + " " + lastName + " con " + age + " años + y la cedula es " + ID
                + "\nEse empleado pertenese a " + deparmentSelected.getName();
        return message;
    }

}
