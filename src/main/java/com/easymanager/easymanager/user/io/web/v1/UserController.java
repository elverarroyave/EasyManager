package com.easymanager.easymanager.user.io.web.v1;

import com.easymanager.easymanager.config.MessageResponse;
import com.easymanager.easymanager.user.io.web.v1.model.UserSaveRequest;
import com.easymanager.easymanager.user.io.web.v1.model.UserSaveResponse;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.UserService;
import com.easymanager.easymanager.user.service.model.UserSaveCmd;
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
@RequestMapping("/api/v1/users")
@Api(tags = {"Users"}, value = "User")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Find all users")
    public List<User> findAll(){
        logger.debug("Begin find all users");

        List<User> usersFound = userService.findAll();

        logger.debug("End find all users");

        return usersFound;

    }

    @PostMapping
    @ApiOperation(value="Create an user")
    public ResponseEntity<Void> create(@RequestBody @Valid UserSaveRequest userToCreate){

        logger.debug("Begin create: userCreate = {}", userToCreate);

        UserSaveCmd userToCreateCmd = UserSaveRequest.toModel(userToCreate);

        User userCreated = userService.register(userToCreateCmd);

        //El estandar de los create dice no devolver un objeto si no la localizacion de este.
        URI location = fromUriString("/api/v1/user").path("/{id}")
                .buildAndExpand(userCreated.getId()).toUri();

        logger.debug("Begin create: userCreate = {}", userCreated);

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find user for id")
    public ResponseEntity<UserSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){

        logger.debug("Begin find user for id={}", id);

        User userFound = userService.findById(id);

        logger.debug("End findUserById: userFound = {}", userFound);

        return ResponseEntity.ok(UserSaveResponse.fromModel(userFound));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an user for id")
    public ResponseEntity<UserSaveResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid UserSaveRequest userToUpdate){

        logger.debug("Begin update: userToUpdate ={}, id = {}",userToUpdate, id);

        UserSaveCmd userToUpdateCmd = UserSaveRequest.toModel(userToUpdate);

        User userUpdated = userService.update(id,userToUpdateCmd);


        logger.debug("End update: userUpdated = {}", userToUpdate);

        return ResponseEntity.ok(UserSaveResponse.fromModel(userUpdated));

    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete an user for id ")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        logger.debug("Begin delete: id = {}", id);

        userService.delete(id);

        logger.debug("End delete: id = {}", id);

        return new ResponseEntity<>(new MessageResponse("User deleted successfully"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "Find an user for email")
    public ResponseEntity<UserSaveResponse> findByEmail(@Valid @PathVariable("email") @NotNull String email){
        logger.debug("Begin find user for email={}", email);

        User userFound = userService.findByEmail(email);

        logger.debug("End findUserByEmail: userFound = {}", userFound);

        return ResponseEntity.ok(UserSaveResponse.fromModel(userFound));
    }

}
