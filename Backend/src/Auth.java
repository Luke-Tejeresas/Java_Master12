package com.javamaster;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class Auth {
    private Connection conn;

    public Auth(Connection conn) {
        this.conn = conn;
    }

    // Register a new user
    public boolean register(String username, String password) {
        try {
            // Check if username exists
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("Username already exists!");
                return false;
            }

            // Hash password
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            // Insert into DB
            String insertQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, hashed);
            insertStmt.executeUpdate();

            System.out.println("User registered successfully!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}