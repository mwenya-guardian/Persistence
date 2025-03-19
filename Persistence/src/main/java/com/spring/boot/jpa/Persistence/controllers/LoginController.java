package com.spring.boot.jpa.Persistence.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController {
    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request){
        System.out.println(request.getUserPrincipal().getName());
        return new ResponseEntity<>(request.getRequestURL().toString(), HttpStatus.OK);
    }
}
