package org.securityapp.services;

import org.securityapp.entities.AppRole;
import org.securityapp.entities.AppUser;
import org.securityapp.repo.AppRoleRepository;
import org.securityapp.repo.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AppRoleRepository appRoleRepository;
    private final AppUserRepository appUserRepository;

    public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String roleName, String userName) {
        AppRole appRole = appRoleRepository.findByRolename(roleName);
        AppUser appUser = appUserRepository.findByUsername(userName);
        appUser.getAppRoles().add(appRole);

    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByUsername(userName);
    }

    @Override
    public List<AppUser> listusers() {
        return appUserRepository.findAll();
    }
}
