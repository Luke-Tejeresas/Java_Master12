package com.javamaster.jmbackend.service;

import com.javamaster.jmbackend.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import com.javamaster.jmbackend.repository.UserRepository;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(String username, String password) {
        if (!username.matches("^[a-zA-Z0-9_.]+$")) {
            return "Username can only contain letters, numbers, underscores, and dots.";
        }

        if (password.length() < 8 ||
                !password.matches(".*[a-zA-Z].*") ||
                !password.matches(".*\\d.*") ||
                !password.matches(".*[^a-zA-Z0-9].*")) {
            return "Password must be at least 8 characters and include letters, numbers, and symbols.";
        }

        if (userRepository.existsByUsername(username)) {
            return "Username already exists.";
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Users newUsers = new Users();
        newUsers.setUsername(username);
        newUsers.setPassword(hashedPassword);
        userRepository.save(newUsers);

        return "User registered successfully!";
    }

    public boolean loginUser(String username, String inputPassword) {
        Users users = userRepository.findByUsername(username);

        if (users == null) {
            System.out.println("Username not found.");
            return false;
        }

        if (BCrypt.checkpw(inputPassword, users.getPassword())) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }
}
