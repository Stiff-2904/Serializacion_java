package com.mycompany.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class DataBase {

    public final String FILE_NAME_EMPLOYEES = "empleados.txt";
    public final String FILE_NAME_DEPARTMENT = "departamentos.txt";

    void menu() {
        int option;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("1.Agregar un empleado y departamento.\n2.Buscar un empleado/departamento.\n3.Eliminar un empleado/departamento\n0.Salir."));
            switch (option) {
                case 1:
                    fillDates();
                    break;

                case 2:
                    search();
                    break;

                case 3:
                    deletes();
                    break;

                case 4:
                    //pruebasEmple();
                    pruebasDepart();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Estas saliendo del programa\nVuelve pronto\nTe extrañaremos");
                    break;

                default:

                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (option != 0);

    }

    void fillDates() {

        String nameEmployee = JOptionPane.showInputDialog(null, "Ingrese el nombre del empleado");

        String lastNameEmployee = JOptionPane.showInputDialog(null, "Ingrese el apellido del empleado");

        int IDEmployee = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cédula del empleado"));

        int ageEmployee = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad del empleado"));

        String departmentName = JOptionPane.showInputDialog(null, "Ingrese el nombre del departamento");

        Department newDepartment = new Department(departmentName);// tengo dudas con eso
        int IDDeparment = newDepartment.getId();
        Employee newEmployee = new Employee(nameEmployee, lastNameEmployee, IDEmployee, ageEmployee, IDDeparment);

        addEmployee(newEmployee);
        addDepartment(newDepartment);
    }

    int addIDEmployee(String departmentName, Department newDepartment) {

        File file = new File(FILE_NAME_DEPARTMENT);
        boolean fileExists = file.exists();
        boolean check = false;

        if (!fileExists) {
            return newDepartment.getId();
        } else {
            try (FileInputStream fis = new FileInputStream(FILE_NAME_DEPARTMENT); ObjectInputStream ois = new ObjectInputStream(fis)) {
                Department deparmentAux;

                while (true) {
                    deparmentAux = (Department) ois.readObject();
                    if (deparmentAux.getName() == departmentName) {
                        return newDepartment.getId();
                        check = true;
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    void addEmployee(Employee newEmployee) {

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
            e.printStackTrace();
        }
    }

    void pruebasEmple() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {
            Employee employe;

            while (true) {
                employe = (Employee) ois.readObject();

                System.out.println(employe.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void pruebasDepart() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {

            Employee employe;

            Department depatamentu;

            while (true) {
                //employe = (Employee) ois.readObject();
                depatamentu = (Department) ois.readObject();

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addDepartment(Department newDepartment) {

        try {
            File file = new File(FILE_NAME_DEPARTMENT);
            boolean fileExists = file.exists();

            if (!fileExists) {
                file.createNewFile();
            }

            ObjectOutputStream objOut;

            if (fileExists) {
                objOut = new ObjectOutputStream(new FileOutputStream(file));
            } else {
                objOut = new MiObjectOutputStream(new FileOutputStream(file, true));
            }

            objOut.writeObject(newDepartment);

            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void search() {
        int option = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Buscar empleado\n2.Buscar departamento"));

        if (option == 1) {
            searchEmployee();
            return;
        }
        if (option == 2) {
            searchDepartment();
            return;
        }
        JOptionPane.showMessageDialog(null, "Numero incorrecto.");
    }

    void searchEmployee() {

        int option = Integer.parseInt(JOptionPane.showInputDialog("1.Buscarlo por medio el nombre\n2.Buscalo por medio de la cedula"));

        if (option == 1) {
            String name = JOptionPane.showInputDialog("Ingrese el nombre del empleado");

            try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {

                Employee auxEmployee;

                while (true) {
                    auxEmployee = (Employee) ois.readObject();

                    if (auxEmployee.getName().equals(name)) {
                        JOptionPane.showMessageDialog(null, auxEmployee.getName());
                    }
                }

            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, name + " nombre no encontrado.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            } catch (EOFException e) {
                System.out.println("Fin del archivo alcanzado");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error de lectura del archivo");
            }
            return;
        }
        if (option == 2) {
            int ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del empleado"));

            try (FileInputStream fis = new FileInputStream(FILE_NAME_EMPLOYEES); ObjectInputStream ois = new ObjectInputStream(fis)) {

                Employee auxEmployee;

                while (true) {
                    auxEmployee = (Employee) ois.readObject();

                    if (auxEmployee.getID() == ID) {
                        JOptionPane.showMessageDialog(null, auxEmployee.getName());
                    }
                }

            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, ID + " cedula no encontrada.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            } catch (EOFException e) {
                System.out.println("Fin del archivo alcanzado");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error de lectura del archivo");
            }
            //return;
        }
    }

    void searchDepartment() {
        String nameDepartment = JOptionPane.showInputDialog(null, "Ingrese el nombre del deparamento.");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\empleados.txt"))) {

            while (true) {
                Department auxDeparment = (Department) ois.readObject();
                if (true) {

                }
                //Employee auxEmployee = (Employee) ois.readObject();

            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        } catch (EOFException e) {
        } catch (IOException e) {
        }

    }

    void deletes() {
        int option = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Eliminar empleado\n2.Eliminar departamento"));

        if (option == 1) {
            deleteEmployee();
            return;
        }
        if (option == 2) {
            deleteDepartment();
            return;

        }
        JOptionPane.showMessageDialog(null, "Numero incorrecto.");
    }

    void deleteEmployee() {
        //fxghchfddddgdfgdfgdfgdfgdfgdf

    }

    void deleteDepartment() {
        //fxghchfddddgdfgdfgdfgdfgdfgdf

    }

}
