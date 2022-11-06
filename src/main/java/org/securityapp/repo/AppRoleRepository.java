package org.securityapp.repo;

import org.securityapp.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
        AppRole findByRolename(String rolename);
}
