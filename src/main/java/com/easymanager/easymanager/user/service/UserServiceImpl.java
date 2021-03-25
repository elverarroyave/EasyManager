package com.easymanager.easymanager.user.service;

import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.model.UserSaveCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserGateway userGateway;

    public UserServiceImpl(UserGateway userGateway){this.userGateway = userGateway;}

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

        User userCreated = userGateway.save(userToCreate);

        logger.debug("End create userCreated = {}", userCreated);

        return userCreated;
    }

    @Override
    public List<User> findAll() {

        logger.debug("Begin find all users");

        List<User> usersFound = userGateway.findAll();

        logger.debug("End find all users");

        return usersFound;
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
                .build();

        User userUpdate = userGateway.update(userToUpdate);

        logger.debug("End update user user");

        return userUpdate;
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

}
