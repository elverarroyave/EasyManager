package com.easymanager.easymanager.user.service;

import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.role.service.RoleGateway;
import com.easymanager.easymanager.user.io.repository.UserRepository;
import com.easymanager.easymanager.user.io.web.v1.model.UserSaveResponse;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.model.UserSaveCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private RoleGateway roleGateway;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserParantrerValidation userParantrerValidation;

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User register(@NotNull UserSaveCmd userToCreateCmd) {

        logger.debug("Begin create userToCreateCmd = {}", userToCreateCmd);

        User userToCreate = UserSaveCmd.toModel(userToCreateCmd);

        //userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));

        userParantrerValidation.userValidation(userToCreate);

        User userCreated = userGateway.save(userToCreate);

        logger.debug("End create userCreated = {}", userCreated);

        return userCreated;
    }

    @Override
    public void addRoleToUser(@NotNull Long idUser, @NotNull Long idRol){

        User userToAddRole = userGateway.findById(idUser);

        Role roleToAddInUser = roleGateway.findById(idRol);

        userToAddRole.addRole(roleToAddInUser);

    }

    @Override
    public List<UserSaveResponse> findAll() {

        logger.debug("Begin find all users");

        List<User> usersFound = userGateway.findAll();

        List<UserSaveResponse> usersFoundResponse = new ArrayList<>();

        for (User user : usersFound ) {
            usersFoundResponse.add(UserSaveResponse.fromModel(user));
        }

        logger.debug("End find all users = {}", usersFoundResponse);

        return usersFoundResponse;
    }

    @Override
    public User update(@NotNull Long id, @NotNull UserSaveCmd userToUpdateCmd) {

        logger.debug("Begin update user: userToUpdateCmd = {}, id={}", userToUpdateCmd, id);

        User userInDataBase = userGateway.findById(id);

        User userToUpdate = userInDataBase.toBuilder()
                .name(userToUpdateCmd.getName())
                .password(userToUpdateCmd.getPassword())
                .typeDocument(userToUpdateCmd.getTypeDocument())
                .numDocument(userToUpdateCmd.getNumDocument())
                .numberPhone(userToUpdateCmd.getNumberPhone())
                .email(userToUpdateCmd.getEmail())
                .address(userToUpdateCmd.getAddress())
                .rolesOfUser(userInDataBase.getRolesOfUser())
                .build();

        userParantrerValidation.userValidation(userToUpdate);

        User userUpdated = userGateway.update(userToUpdate);

        logger.debug("End update user user");

        return userUpdated;
    }

    @Override
    public void delete(@NotNull Long id) {
        logger.debug("Begin deleteDyId id={}", id);

        userGateway.deleteById(id);

        logger.debug("End deleteById id={}", id);
    }

    @Override
    public User findById(@NotNull Long id) {

        logger.debug("Begin find user for id={}", id);

        User userFound = userGateway.findById(id);

        logger.debug("End find user for userFound={}", userFound);

        return userFound;
    }

    @Override
    public User findByEmail(@NotNull String email){

        logger.debug("Begin find user for email = {}", email);

        User userFound = userGateway.findByEmail(email);

        logger.debug("End find user for email userFound = {}" , userFound);

        return userFound;
    }

    @Override
    public User findByDocument(@NotNull String numDocument) {

        logger.debug("Begin find user for numDocument={}", numDocument);

        User userFound = userGateway.findByDocument(numDocument);

        logger.debug("End find user for userFound={}", userFound);

        return userFound;
    }

    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean existByUserName(String userName){
        return userRepository.existSByUserName(userName);
    }


    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }

}
