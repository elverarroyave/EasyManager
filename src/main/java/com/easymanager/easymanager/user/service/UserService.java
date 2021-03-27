package com.easymanager.easymanager.user.service;

import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.model.UserSaveCmd;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    User register(@NotNull UserSaveCmd userSaveRequest);

    List<User> findAll();

    User update(@NotNull Long id,@NotNull UserSaveCmd userToUpdateCmd);

    void delete(@NotNull Long id);

    User findById(@NotNull Long id);

    User findByEmail(@NotNull String email);
}
