package com.mycompany.main;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int nextId = 1;//ver de que numero se incia la secuencia
    private int id;
    private String name;

    public Department(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String messageEmplo(Employee employeeSelected) {
        String message = "El departamento " + name + " y los empleados que pertenecen a este departamento: ";

        return message;
    }

}
