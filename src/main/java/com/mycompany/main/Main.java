package com.mycompany.main;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido a la base de datos de la empresa GOOGLE\nBy Stiff");
        DataBase dataBase = new DataBase();
        dataBase.menu();
    }
}
