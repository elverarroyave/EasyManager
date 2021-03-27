package com.easymanager.easymanager.role.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.role.io.repository.RoleRepository;
import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.role.service.RoleGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class RoleGatewayImpl implements RoleGateway {

    @Autowired
    private RoleRepository roleRepository;

    //public RoleGatewayImpl(RoleRepository roleRepository){this.roleRepository = roleRepository;}

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Role save(@NotNull Role roleToCreate) {

        logger.debug("Begin save role = {}", roleToCreate);

        Role roleCreated = roleRepository.save(roleToCreate);

        logger.debug("End save role = {}", roleCreated);

        return roleCreated;
    }

    @Override
    public Role update(@NotNull Role roleToUpdate) {

        logger.debug("Begin update role = {}", roleToUpdate);

        Role roleUpdated = roleRepository.save(roleToUpdate);

        logger.debug("End save update = {}", roleUpdated);

        return roleUpdated;
    }

    @Override
    public void delete(@NotNull Long id) {

        logger.debug("Begin delete role for id = {}", id);

        roleRepository.deleteById(id);

        logger.debug("End delete role for id");
    }

    @Override
    public Role findById(@NotNull Long id) {

        logger.debug("Begin find role by id = {}", id);

        Role roleFound = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Role not found, please check it id"));

        logger.debug("End find role by id = {}", id);

        return roleFound;
    }

    @Override
    public List<Role> findAll() {

        logger.debug("Begin find all roles");

        List<Role> roles = roleRepository.findAll();

        logger.debug("End find all roles");

        return roles;
    }

    @Override
    public List<Role> findActives() {

        logger.debug("Begin active roles");

        List<Role> activeRoles = roleRepository.findActive();

        logger.debug("End find active roles");

        return activeRoles;
    }

    @Override
    public List<Role> findUnables() {
        logger.debug("Begin unable roles");

        List<Role> unableRoles = roleRepository.findUnable();

        logger.debug("End find unable roles");

        return unableRoles;
    }
}
