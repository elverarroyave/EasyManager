package com.easymanager.easymanager.user.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.user.io.repository.UserRepository;
import com.easymanager.easymanager.user.service.UserGateway;
import com.easymanager.easymanager.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserGategayImpl implements UserGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;

    public UserGategayImpl(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public User save(@NotNull User userToCreate) {

        logger.debug("Begin create userToCreate = {}", userToCreate);

        final User userToBeCreated = userToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        final User userCreated = userRepository.save(userToBeCreated);

        logger.debug("End create userToCreate = {}", userCreated);

        return userCreated;
    }

    @Override
    public User findById(@NotNull Long id) {

        logger.debug("Begin find by id={}", id);

        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Id not found. Check your id please."));

        logger.debug("End find by id={}", id);

        return userFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin delete user for id={}", id);

        findById(id);
        userRepository.deleteById(id);

        logger.debug("End delete user");
    }

    @Override
    public User update(@NotNull User userToUpdate) {

        logger.debug("Begin update userToUpdate = {}", userToUpdate);

        final User userToBeUpdate = userToUpdate.toBuilder()
                .updateDate(LocalDateTime.now()).build();

        final User userUpdated = userRepository.save(userToBeUpdate);

        logger.debug("End update userUpdated = {}", userUpdated);

        return userUpdated;
    }

    @Override
    public List<User> findAll() {

        logger.debug("Begin find all users");

        List<User> usersFound = userRepository.findAll();

        logger.debug("End find all users");

        return usersFound;
    }

    @Override
    public User findByEmail(@NotNull String email) {

        logger.debug("Begin find find user for: email={}", email);

        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundExeption("User not found, please check your email"));

        logger.debug("End find user for email userFound={}", userFound);

        return userFound;
    }
}
