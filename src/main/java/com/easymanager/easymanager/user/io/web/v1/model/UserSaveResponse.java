package com.easymanager.easymanager.user.io.web.v1.model;

import com.easymanager.easymanager.role.model.Role;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.model.UserSaveCmd;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserSaveResponse {
    private String name;

    private String password;

    private String typeDocument;

    private String numDocument;

    private String numberPhone;

    private String email;

    private String address;

    private Set<Role> rolesOfUser;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static UserSaveResponse fromModel(User userToResponse){
        return UserSaveResponse.builder()
                .name(userToResponse.getName())
                .password(userToResponse.getPassword())
                .typeDocument(userToResponse.getTypeDocument())
                .numDocument(userToResponse.getNumDocument())
                .numberPhone(userToResponse.getNumberPhone())
                .email(userToResponse.getEmail())
                .address(userToResponse.getAddress())
                .rolesOfUser(userToResponse.getRolesOfUser())
                .createDate(userToResponse.getCreateDate())
                .updateDate(userToResponse.getUpdateDate())
                .build();
    }
}
