package com.javamaster.jmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javamaster.jmbackend.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);
    Users findByUsername(String username);
}
