package com.mycompany.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DataBase {

    public final String FILE_NAME_EMPLOYEES = "employes.txt";
    public final String FILE_NAME_DEPARTMENT = "deparment.txt";

    boolean empleadosAgregados = false;
    boolean departamentosAgregados = false;

    void menu() {
        int option;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("1. Agregar empleado.\n2. Agregar departamento.\n3. Agregar empleado a departamento.\n4. Buscar Empleado\n5. Buscar departamento\n6. Eliminar Empleado\n7. Eliminar Departamento\n\n0. Salir"));

            if (option == 3 && !(empleadosAgregados && departamentosAgregados)) {
                JOptionPane.showMessageDialog(null, "Debe agregar al menos un empleado y un departamento primero.");
                continue;

            } else if ((option == 4 || option == 5 || option == 6 || option == 7) && !(empleadosAgregados || departamentosAgregados)) {
                JOptionPane.showMessageDialog(null, "No hay empleados o departamentos para realizar esta operación.");
                continue;
            }

            switch (option) {
                case 1:
                    fillDataEmployee();
                    empleadosAgregados = true;
                    break;

                case 2:
                    fillDataDepartment();
                    departamentosAgregados = true;
                    break;

                case 3:
                    addEmployeeToDeparment();
                    break;

                case 4:
                    searchEmployee();
                    break;

                case 5:
                    searchDepartment();
                    break;

                case 6:
                    deleteEmployee();
                    break;

                case 7:
                    deleteDepartment();
                    break;

                case 9:
                    pruebasEmple();
                    pruebasDepart();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Estás saliendo del programa.\n¡Vuelve pronto!\n¡Te extrañaremos!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (option != 0);
    }

    void fillDataEmployee() {
        String nameEmployee = JOptionPane.showInputDialog(null, "Ingrese el nombre del empleado");
        if (nameEmployee.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del empleado es requerido");
            return;
        }

        String lastNameEmployee = JOptionPane.showInputDialog(null, "Ingrese el apellido del empleado:");
        if (lastNameEmployee.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El apellido del empleado es requerido");
            return;
        }

        String IDEmployee;
        boolean validID = false;
        do {
            IDEmployee = JOptionPane.showInputDialog(null, "Ingrese el número de cédula del empleado:");
            validID = validateID(IDEmployee);
            if (!validID) {
                JOptionPane.showMessageDialog(null, "Número de cédula inválido. Por favor ingrese 11 dígitos sin guiones ni espacios.");
            }
        } while (!validID);

        int ageEmployee;
        try {
            ageEmployee = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad del empleado:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número entero");
            return;
        }

        Employee newEmployee = new Employee(nameEmployee, lastNameEmployee, Integer.parseInt(IDEmployee), ageEmployee);

        addEmployeeToTXT(newEmployee);
        JOptionPane.showMessageDialog(null, "Empleado " + nameEmployee + " " + lastNameEmployee + " creado con éxito!");
    }

    void fillDataDepartment() {
        String departmentName = JOptionPane.showInputDialog(null, "Ingrese el nombre del departamento: ");
        if (departmentName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del departamento es requerido");
            return;
        }

        Department newDepartment = new Department(departmentName);

        addDepartmentToTXT(newDepartment);
        JOptionPane.showMessageDialog(null, "Departamento " + departmentName + " creado con exito!!");
    }

    void addEmployeeToDeparment() {
        String nameEmployee = JOptionPane.showInputDialog(null, "Ingrese el nombre del trabajador.");
        if (nameEmployee.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del empleado es requerido");
            return;
        }

        if (!(searchNameEmployeeDB(nameEmployee) == null)) {
            Employee employeeAux = searchNameEmployeeDB(nameEmployee);

            String nameDeparment = JOptionPane.showInputDialog(null, "Ingrese el departamento.");

            if (!(searchNameDeparmentDB(nameDeparment) == null)) {
                Department departmentAux;
                departmentAux = searchNameDeparmentDB(nameDeparment);
                employeeAux.setID(departmentAux.getId());
            } else {
                JOptionPane.showMessageDialog(null, "El departamento que ingreso no esta en la base de datos\nIngresalo para poder seguir con el registro.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El nombre que ingreso no esta en la base de datos\nIngresalo para poder seguir con el registro.");
        }
    }

    Employee searchNameEmployeeDB(String nameEmployee) {
        try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {
            Employee employe;

            while (true) {
                employe = (Employee) ois.readObject();
                if (employe.getName().equals(nameEmployee)) {
                    return employe;
                }
            }

        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {

        }
        return null;
    }

    Department searchNameDeparmentDB(String nameDeparment) {
        try (FileInputStream fis = new FileInputStream(FILE_NAME_DEPARTMENT); ObjectInputStream ois = new ObjectInputStream(fis)) {
            Department departmentSelected;

            while (true) {
                departmentSelected = (Department) ois.readObject();
                if (departmentSelected.getName().equals(nameDeparment)) {
                    return departmentSelected;
                }
            }

        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {

        }
        return null;
    }

    void addEmployeeToTXT(Employee newEmployee) {

        try {
            File file = new File(FILE_NAME_EMPLOYEES);
            boolean fileExists = file.exists();

            if (!fileExists) {
                file.createNewFile();
            }

            ObjectOutputStream objOut;

            if (file.length() == 0) {
                objOut = new ObjectOutputStream(new FileOutputStream(file));
            } else {
                objOut = new MiObjectOutputStream(new FileOutputStream(file, true));
            }

            objOut.writeObject(newEmployee);

            objOut.close();
        } catch (IOException e) {

        }
    }

    void addDepartmentToTXT(Department newDepartment) {

        try {
            File file = new File(FILE_NAME_DEPARTMENT);
            boolean fileExists = file.exists();

            if (!fileExists) {
                file.createNewFile();
            }

            ObjectOutputStream objOut;

            if (file.length() == 0) {
                objOut = new ObjectOutputStream(new FileOutputStream(file));
            } else {
                objOut = new MiObjectOutputStream(new FileOutputStream(file, true));
            }

            objOut.writeObject(newDepartment);

            objOut.close();
        } catch (IOException e) {

        }
    }

    void searchEmployee() {
        String searchFlag = JOptionPane.showInputDialog("Ingresa nombre/apellido del empleado");

        try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {
            Employee auxEmployee;

            while (true) {
                auxEmployee = (Employee) ois.readObject();

                if (auxEmployee.getName().equals(searchFlag) || auxEmployee.getLastName().equals(searchFlag)) {
                    JOptionPane.showMessageDialog(null, auxEmployee.toString());
                }
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, searchFlag + " empleado no encontrado.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (EOFException e) {

        } catch (IOException e) {

        }

    }

    void searchDepartment() {
        String nameDepartment = JOptionPane.showInputDialog("Ingresa nombre del departamento");

        try (FileInputStream fis = new FileInputStream(FILE_NAME_DEPARTMENT); ObjectInputStream ois = new ObjectInputStream(fis)) {

            Department auxDepartment;

            while (true) {
                auxDepartment = (Department) ois.readObject();

                if (auxDepartment.getName().equals(nameDepartment)) {
                    JOptionPane.showMessageDialog(null, auxDepartment.toString());
                }
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, nameDepartment + " departamento no encontrado.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (EOFException e) {

        } catch (IOException e) {

        }
    }

    void deleteEmployee() {
        String searchFlag = JOptionPane.showInputDialog("Ingresa nombre/apellido del empleado");

        if (searchFlag.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puede dejar el espacio en blanco");
            return;
        }

        boolean userFound = false;

        try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {

            Employee employe;
            List<Employee> employees = new ArrayList<>();

            while (true) {
                try {
                    employe = (Employee) ois.readObject();
                    if (employe.getName().equalsIgnoreCase(searchFlag) || employe.getLastName().equalsIgnoreCase(searchFlag)) {
                        userFound = true;
                    } else {
                        employees.add(employe);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            if (!userFound) {
                JOptionPane.showMessageDialog(null, "El empleado '" + searchFlag + "' no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ois.close();

            File file = new File(FILE_NAME_EMPLOYEES);

            System.out.println(file.delete());

            for (Employee emp : employees) {
                addEmployeeToTXT(emp);
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    void deleteDepartment() {
        String nameDepartment = JOptionPane.showInputDialog("Ingresa nombre del departamento");

        if (nameDepartment.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puede dejar el espacio en blanco");
            return;
        }

        boolean departmentFound = false;

        try (FileInputStream fis = new FileInputStream(FILE_NAME_DEPARTMENT); ObjectInputStream ois = new ObjectInputStream(fis)) {

            Department department;
            List<Department> departments = new ArrayList<>();

            while (true) {
                try {
                    department = (Department) ois.readObject();
                    if (department.getName().equalsIgnoreCase(nameDepartment)) {
                        departmentFound = true;
                    } else {
                        departments.add(department);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            if (!departmentFound) {
                JOptionPane.showMessageDialog(null, "El departamento '" + nameDepartment + "' no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ois.close();

            File file = new File(FILE_NAME_DEPARTMENT);

            System.out.println(file.delete());

            for (Department dep : departments) {
                addDepartmentToTXT(dep);
            }

        } catch (ClassNotFoundException | IOException e) {

        }
    }

    boolean validateID(String ID) {
        if (ID.length() != 11) {
            return false;
        }

        for (int i = 0; i < ID.length(); i++) {
            if (!Character.isDigit(ID.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // solo pruebas, se puede eliminar
    void pruebasEmple() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {
            Employee employe;

            while ((employe = (Employee) ois.readObject()) != null) {

                System.out.println(employe.toString());
            }

        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {

        }
    }

    void pruebasDepart() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME_DEPARTMENT); ObjectInputStream ois = new ObjectInputStream(fis)) {

            Department depatamentu;

            while (true) {
                depatamentu = (Department) ois.readObject();
                System.out.println(depatamentu.toString());
            }

        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {
        }
    }

}
