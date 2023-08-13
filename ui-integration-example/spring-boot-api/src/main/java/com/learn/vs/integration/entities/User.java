package com.learn.vs.integration.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String fullname;

    @NotNull
    private String password;



    @NotNull
    private String role;

    public void setId(Long id) {
        this.id = id;
    }

    protected User() {
    }

    public User(Long id, String username, String fullname, String password, String role) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
