package com.mycompany.main;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int nextId = 0;
    private int idDepartment;
    private String name;

    public Department(String name) {
        this.idDepartment = indexIncrease();
        this.name = name;
    }
    
    public int indexIncrease(){
        setNextId(getNextId() + 1);
        return getNextId();
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Department.nextId = nextId;
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

    public String toString() {
        String message = "{El departamento " + name + " y el id de ese departamento es " + idDepartment + "}\n";

        return message;
    }

}
