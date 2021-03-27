package com.easymanager.easymanager.role.service;

import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.role.service.model.RoleSaveCmd;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoleService {

    Role create(@NotNull RoleSaveCmd roleToCreate);

    Role findById(@NotNull Long id);

    Role update(@NotNull Long id,@NotNull Role RoleToUpdate);

    void deleteById(@NotNull Long id);

    List<Role> findAll();

    List<Role> findActives();

    List<Role> findUnables();

}
