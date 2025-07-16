package com.HelloWorldJDBC;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class UsingDriverManager {
    private final static String CONN_STRING = "jdbc:mysql://localhost:3306";
    public static void main(String[] args) {


        //Get user input for username and password
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf,"Enter DB Password", JOptionPane.OK_CANCEL_OPTION);;
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;

        //try to stablish a connection
        try ( Connection connection = DriverManager.getConnection(CONN_STRING, username, String.valueOf(password))){
            System.out.println("Connection stablished successfully.");
            Arrays.fill(password, ' ');
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //erase password from memory
    }
}
