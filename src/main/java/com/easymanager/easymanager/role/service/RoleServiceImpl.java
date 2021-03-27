package com.easymanager.easymanager.role.service;

import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.role.service.model.RoleSaveCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    public RoleGateway roleGateway;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Role create(@NotNull RoleSaveCmd roleToCreateCmd) {

        logger.debug("Begin create an role = {}", roleToCreateCmd);

        Role roleToCreate = RoleSaveCmd.toModel(roleToCreateCmd);

        Role roleCreated = roleGateway.save(roleToCreate);

        logger.debug("End create an role = {}", roleCreated);

        return roleCreated;
    }

    @Override
    public Role findById(@NotNull Long id) {
        return null;
    }

    @Override
    public Role update(@NotNull Long id, @NotNull Role RoleToUpdate) {
        return null;
    }

    @Override
    public void deleteById(@NotNull Long id) {

    }

    @Override
    public List<Role> findAll() {

        logger.debug("Begin find all role");

        List<Role> rolesFound = roleGateway.findAll();

        logger.debug("End find all roles");

        return rolesFound;
    }

    @Override
    public List<Role> findActives() {
        return null;
    }

    @Override
    public List<Role> findUnables() {
        return null;
    }
}
