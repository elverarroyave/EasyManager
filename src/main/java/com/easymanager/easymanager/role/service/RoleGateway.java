package com.easymanager.easymanager.role.service;

import com.easymanager.easymanager.role.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoleGateway {
    Role save(@NotNull Role roleToCreate);

    Role update(@NotNull Role roleToUpdate);

    void delete(@NotNull Long id);

    Role findById(@NotNull Long id);

    List<Role> findAll();

    List<Role> findActives();

    List<Role> findUnables();
}
