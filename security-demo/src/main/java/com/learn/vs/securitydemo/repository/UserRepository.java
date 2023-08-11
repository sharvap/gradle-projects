package com.learn.vs.securitydemo.repository;

import com.learn.vs.securitydemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByusername(String username);
}
