package com.learn.vs.securitydemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@EnableMethodSecurity
public class GenericController {

    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping("normal")
   public ResponseEntity<String>  getHomePage(){
        return ResponseEntity.ok("Yes, ");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("admin")
    String getAdminPage(){
        return "Admin Page";
    }
}
