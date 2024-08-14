package com.spring.jwt.implementation.jwt.controller;

import com.spring.jwt.implementation.jwt.dto.AuthenticateDTO;
import com.spring.jwt.implementation.jwt.entity.AuthenticateEntity;
import com.spring.jwt.implementation.jwt.service.impl.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/authValidation")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService; // For storing the records when the db is initialized

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthenticateDTO authenticateDTO) {
        return this.authenticateService.generateToken(authenticateDTO.getUsername());
    }

    @GetMapping("/welcome")
    public String displayWelcome(){
        return "Hi! Greetings & welcome";
    }

//    @GetMapping("/all")
//    public List<AuthenticateEntity> displayAllProducts() {
//        return authenticateService.getAllProducts();
//    }


   }
