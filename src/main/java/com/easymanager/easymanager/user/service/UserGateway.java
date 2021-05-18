package com.easymanager.easymanager.user.service;

import com.easymanager.easymanager.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface UserGateway {

    User save(@NotNull User userToCreate);

    User findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    User update(@NotNull User userToUpdate);

    List<User> findAll();

    User findByEmail(@NotNull String email);

    Optional<User> verifyEmail(@NotNull String email);
}
