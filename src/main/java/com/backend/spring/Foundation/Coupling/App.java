package com.backend.spring.Foundation.Coupling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(com.backend.spring.Application.class, args);
    }

/*
    // Tight coupling : Code is rigidly dependent on specific class implementations
    userService userService = new userService(new PostgresDatabase());
    @Override
    public void run(String... args) throws Exception {
        userService.saveUser("I am the user");
    }
*/

    // Loose Coupling achieved : No new() keyword for new object
    public App(userService userService) {
        this.userService = userService;
    }

    //@Autowired(DI) : Not recommended. Why?
    private final userService userService;
    @Override
    public void run(String... args) throws Exception {
        userService.saveUser("I am the user");
    }
}
