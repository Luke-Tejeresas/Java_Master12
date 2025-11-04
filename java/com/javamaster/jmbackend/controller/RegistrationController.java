package com.javamaster.jmbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javamaster.jmbackend.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestParam String username,
                                                        @RequestParam String password) {
        String result = registrationService.registerUser(username, password);

        if (result.contains("successfully")) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", result
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", result
            ));
        }
    }

    // ✅ Login user
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username,
                                                     @RequestParam String password) {
        boolean success = registrationService.loginUser(username, password);

        if (success) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Login successful!"
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "status", "error",
                    "message", "Invalid username or password."
            ));
        }
    }
}