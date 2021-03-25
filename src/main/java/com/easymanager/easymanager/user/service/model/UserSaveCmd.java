package com.easymanager.easymanager.user.service.model;

import com.easymanager.easymanager.user.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserSaveCmd {

    private String name;

    private String password;

    private String typeDocument;

    private String numDocument;

    private String numberPhone;

    private String email;

    private String address;

    public static User toModel(UserSaveCmd userToCreateCmd){
        return User.builder()
                .name(userToCreateCmd.getName())
                .password(userToCreateCmd.getPassword())
                .typeDocument(userToCreateCmd.getTypeDocument())
                .numDocument(userToCreateCmd.getNumDocument())
                .numberPhone(userToCreateCmd.getNumberPhone())
                .email(userToCreateCmd.getEmail())
                .address(userToCreateCmd.getAddress())
                .build();
    }

}
