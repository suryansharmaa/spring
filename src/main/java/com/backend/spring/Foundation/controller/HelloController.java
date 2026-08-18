package com.backend.spring.Foundation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // This demonstrates the string on the port
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}
