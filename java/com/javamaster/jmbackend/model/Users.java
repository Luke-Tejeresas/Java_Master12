package com.javamaster.jmbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accinfo")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accUsername", unique = true, nullable = false)
    private String username;

    @Column(name = "accPassword", nullable = false)
    private String password;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
