package com.learn.vs.integration.repository;

import com.learn.vs.integration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByusername(String username);
}
