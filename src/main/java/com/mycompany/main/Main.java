package com.mycompany.main;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido a la base de datos de la empresa Z A G A N O \nCEO: Stiff & Marco");
        DataBase dataBase = new DataBase();
        dataBase.menu();
    }
}
