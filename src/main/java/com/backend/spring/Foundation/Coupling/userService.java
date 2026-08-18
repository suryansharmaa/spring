package com.backend.spring.Foundation.Coupling;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class userService {
/*
    // Tight Coupling : If you want to switch between databases, need to manually track down and rewrite code everywhere.
    mySQLDatabase myDatabase = new mySQLDatabase();

    public void saveUser(String user) {
        System.out.println("userService is saving user");
        myDatabase.saveDatabase(user);
    }
*/

/*
 Loose Coupling : Can switch between diff databases without changing code
    Database database;
    public userService(Database database) {
        this.database = database;
    }

    public void saveUser(String user) {
        System.out.println("userService is saving user");
        database.save(user);
    }
*/

    //Now how do we switch between databases?
    Database database;
/*
     1. using Qualifiers (Not recommended) : need to change code to vary qualifer
    public userService(@Qualifier("postgresDatabase") Database database) {
        this.database = database;
    }

    2. Using Primary annotation... suitable only if want to use one by default

    3. Using Profiles annotation - prod, dev etc
       ... not the best way to do it ... suitable for environment based seperation
*/

    // 4. Best way to do it? ConditionOnProperty annotation

/*
     ***  But we don't inject this way  ***
    @Value("${db.type}")
    String property;
*/

    configurations configuration;
    public userService(Database database, configurations configuration) {
        this.database = database;
        this.configuration = configuration;
    }

    public void saveUser(String user) {
        System.out.println("userService is saving user : "+ "with type " +configuration.getType());
        database.save(user);
    }
}
