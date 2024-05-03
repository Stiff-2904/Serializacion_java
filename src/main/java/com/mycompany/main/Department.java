package com.mycompany.main;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {

    private String name;
    private String ubication;
    private List<Employee> empleados;

    public Department(String name, String ubication, Employee employee) {
        this.name = name;
        this.ubication = ubication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public List<Employee> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Employee> empleados) {
        this.empleados = empleados;
    }

    public String messageEmplo(Employee employeeSelected) {
        String message = "El departamento " + name + " esta ubicado en " + ubication + " y los empleados que pertenecen a este departamento: ";

        return message;
    }

}
