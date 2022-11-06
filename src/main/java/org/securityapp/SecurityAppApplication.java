package org.securityapp;

import org.securityapp.entities.AppRole;
import org.securityapp.entities.AppUser;
import org.securityapp.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityAppApplication.class, args);
    }

    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));
            accountService.addNewRole(new AppRole(null,"CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));

            accountService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"admin","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user2","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user3","1234",new ArrayList<>()));

            accountService.addRoleToUser("USER","user1");
            accountService.addRoleToUser("ADMIN","user1");
            accountService.addRoleToUser("USER","user2");
            accountService.addRoleToUser("CUSTOMER_MANAGER","user2");
            accountService.addRoleToUser("USER","user3");
            accountService.addRoleToUser("ADMIN","user3");
            accountService.addRoleToUser("PRODUCT_MANAGER","admin");
            accountService.addRoleToUser("USER","admin");

        };
    }

}
