package com.mycompany.main;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int nextId = 1;    //ver de que numero se incia la secuencia
    private int idDepartment;
    private String name;

    public Department(String name) {
        this.idDepartment = nextId++;
        this.name = name;
    }

    public int getId() {
        return idDepartment;
    }

    public void setId(int id) {
        this.idDepartment = id;
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
