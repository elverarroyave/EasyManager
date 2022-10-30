package com.easymanager.easymanager.role.io.repository;

import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM ROLE r WHERE r.state = true")
    List<Role> findActive();

    @Query("SELECT r FROM ROLE r WHERE r.state = false")
    List<Role> findUnable();

    Optional<Role> findByRoleName(RolNombre rolNombre);
}
