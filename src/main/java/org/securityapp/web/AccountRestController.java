package org.securityapp.web;


import lombok.Data;
import org.securityapp.entities.AppRole;
import org.securityapp.entities.AppUser;
import org.securityapp.services.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/users")
    AppUser saveUser(@RequestBody AppUser appUser){
        return  accountService.addNewUser(appUser);
    }
    @PostMapping(path = "/roles")
    AppRole saveRole(AppRole appRole){
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUserName(), roleUserForm.getRoleName());
    }
}

@Data
class RoleUserForm{
    private String userName;
    private String roleName;
}
