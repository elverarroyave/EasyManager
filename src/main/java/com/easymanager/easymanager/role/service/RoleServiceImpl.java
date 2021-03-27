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

        logger.debug("Begin find role by id = {}", id);

        Role roleFound = roleGateway.findById(id);

        logger.debug("End find role by id ");

        return roleFound;
    }

    @Override
    public Role update(@NotNull Long id, @NotNull RoleSaveCmd roleToUpdateCmd) {

        logger.debug("Begin update role: id = {}, RoleToUpdate={}", id, roleToUpdateCmd);

        Role roleInDataBase = roleGateway.findById(id);

        Role roleToUpdate = roleInDataBase.toBuilder()
                .name(roleToUpdateCmd.getName())
                .description(roleToUpdateCmd.getDescription())
                .state(roleToUpdateCmd.isStatus())
                .build();

        Role roleUpdated = roleGateway.update(roleToUpdate);

        logger.debug("End update role: roleUpdated = {}", roleUpdated);

        return roleUpdated;

    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin delete role by id");

        roleGateway.delete(id);

        logger.debug("End delete role by id");
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

        logger.debug("Begin findActive roles");

        List<Role> activeRoles = roleGateway.findActives();

        logger.debug("End findActive roles");

        return activeRoles;
    }

    @Override
    public List<Role> findUnables() {
        logger.debug("Begin findUnable roles");

        List<Role> unableRoles = roleGateway.findUnables();

        logger.debug("End findUnable roles");

        return unableRoles;
    }
}
