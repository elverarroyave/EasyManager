package com.easymanager.easymanager.role.io.web.v1;

import com.easymanager.easymanager.role.io.web.v1.model.RoleSaveRequest;
import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.role.service.RoleService;
import com.easymanager.easymanager.role.service.model.RoleSaveCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/api/v1/roles")
@Api(tags = {"Roles"})
public class RoleController {

    @Autowired
    RoleService roleService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    @ApiOperation(value = "find all roles")
    public List<Role> findAll(){

        logger.debug("Begin find all roles");

        List<Role> rolesFound = roleService.findAll();

        logger.debug("End find all roles rolesFound = {}",rolesFound);

        return rolesFound;
    }

    @PostMapping
    @ApiOperation(value = "Create an role")
    public ResponseEntity<Void> create(@RequestBody @Valid RoleSaveRequest roleSaveRequest){

        logger.debug("Begin create role: = {}", roleSaveRequest);

        RoleSaveCmd roleToCreateCmd = RoleSaveRequest.toModel(roleSaveRequest);

        Role roleCreated = roleService.create(roleToCreateCmd);

        //El estandar de los create dice no devolver un objeto si no la localizacion de este.
        URI location = fromUriString("/api/v1/role").path("/{id}")
                .buildAndExpand(roleCreated.getId()).toUri();

        logger.debug("End create role: = {}", roleCreated);

        return ResponseEntity.created(location).build();

    }


}
