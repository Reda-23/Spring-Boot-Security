package org.securityapp.web;


import org.securityapp.entities.AppUser;
import org.securityapp.services.AccountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AccountRestController {

    private final AccountServiceImpl accountService;

    public AccountRestController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    List<AppUser> appUserList(){
        return accountService.listusers();
    }
}
