package com.easymanager.easymanager.role.io.web.v1;

import com.easymanager.easymanager.config.MessageResponse;
import com.easymanager.easymanager.role.io.web.v1.model.RoleSaveRequest;
import com.easymanager.easymanager.role.io.web.v1.model.RoleSaveResponse;
import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.role.service.RoleService;
import com.easymanager.easymanager.role.service.model.RoleSaveCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    @ApiOperation(value = "find all roles.")
    public List<Role> findAll(){

        logger.debug("Begin find all roles");

        List<Role> rolesFound = roleService.findAll();

        logger.debug("End find all roles rolesFound = {}",rolesFound);

        return rolesFound;
    }

    @PostMapping
    @ApiOperation(value = "Create an role.")
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

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an role by id.")
    public ResponseEntity<RoleSaveResponse> findById(@Valid @PathVariable @NotNull Long id){

        logger.debug("Begin find role by id = {}", id);

        Role roleFound = roleService.findById(id);

        logger.debug("End find role by id");

        return ResponseEntity.ok(RoleSaveResponse.fromModel(roleFound));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an role by id.")
    ResponseEntity<MessageResponse> delete(@PathVariable @NotNull Long id){
        logger.debug("Begin delete role");

        roleService.deleteById(id);

        logger.debug("End delete role");

        return new ResponseEntity<>(new MessageResponse("Role deleted succesfully"), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an role by id")
    public ResponseEntity<RoleSaveResponse> update(@PathVariable @NotNull  Long id,
                                                   @RequestBody @Valid RoleSaveRequest roleToUpdate){

        logger.debug("Begin update an role: id = {}, roleToUpdate = {}", id, roleToUpdate);

        RoleSaveCmd roleToUpdateCmd = RoleSaveRequest.toModel(roleToUpdate);

        Role roleUpdated = roleService.update(id, roleToUpdateCmd);

        logger.debug("End update an role: roleUpdated = {}", roleUpdated);

        return ResponseEntity.ok(RoleSaveResponse.fromModel(roleUpdated));

    }

    @GetMapping("/actives")
    @ApiOperation(value = "find all roles.")
    public List<Role> findActives(){

        logger.debug("Begin find active roles");

        List<Role> rolesFound = roleService.findActives();

        logger.debug("End find active roles rolesFound = {}",rolesFound);

        return rolesFound;
    }

    @GetMapping("/unables")
    @ApiOperation(value = "find all roles.")
    public List<Role> findUnables(){

        logger.debug("Begin find unable roles");

        List<Role> rolesFound = roleService.findUnables();

        logger.debug("End find unable roles rolesFound = {}",rolesFound);

        return rolesFound;
    }


}
