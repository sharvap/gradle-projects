package com.learn.vs.integration.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class GenericController {


    @RequestMapping("normal")
   public ResponseEntity<String>  getHomePage(){
        return ResponseEntity.ok("Yes, ");
    }

    @RequestMapping("admin")
    String getAdminPage(){
        return "Admin Page";
    }
}
