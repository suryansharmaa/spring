package com.backend.spring.Foundation.Coupling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile("dev")
@ConditionalOnProperty(name = "db.type", havingValue = "mySQL")
public class mySQLDatabase implements Database{

/*
    Tight Coupling Example
    public void saveDatabase(String user) {
        System.out.println("mySQLDatabase is saving user");
    }
*/

    @Override
    public void save(String user) {
        System.out.println("mySQLDatabase is saving user");
    }
}
