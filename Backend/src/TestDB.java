package com.javamaster;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        try {
            // Use the database and user we just created
            String url      = "jdbc:mysql://localhost:3306/javamasterdb?useSSL=false&serverTimezone=UTC";
            String user     = "javamaster";         // the dedicated user
            String password = "javjav";   // the password you set

            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.println("Connected successfully!");

            conn.close(); // always close the connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}