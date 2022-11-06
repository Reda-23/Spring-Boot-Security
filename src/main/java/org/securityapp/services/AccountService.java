package org.securityapp.services;

import org.securityapp.entities.AppRole;
import org.securityapp.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String roleName,String userName);
    AppUser loadUserByUserName(String userName);
    List<AppUser> listusers();
}
