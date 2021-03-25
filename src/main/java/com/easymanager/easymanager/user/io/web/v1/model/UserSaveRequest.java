package com.easymanager.easymanager.user.io.web.v1.model;

import com.easymanager.easymanager.user.service.model.UserSaveCmd;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserSaveRequest {

    @NotNull(message = "Your name is required")
    @NotBlank(message = "Your name cannot be blank")
    private String name;

    @NotNull(message = "Your password is required")
    @NotBlank(message = "Your password cannot be blank")
    private String password;

    @NotNull(message = "Your type document is required")
    @NotBlank(message = "Your type document cannot be blank")
    private String typeDocument;

    @NotNull(message = "Your document number is required")
    @NotBlank(message = "Your document number cannot be blank")
    private String numDocument;

    private String numberPhone;

    @NotNull(message = "Your email address is required")
    @NotBlank(message = "Your email address cannot be blank")
    private String email;

    private String address;

    public static UserSaveCmd toModel(UserSaveRequest userToCreateRequest){
        return UserSaveCmd.builder()
                .name(userToCreateRequest.getName())
                .password(userToCreateRequest.getPassword())
                .typeDocument(userToCreateRequest.getTypeDocument())
                .numDocument(userToCreateRequest.getNumDocument())
                .numberPhone(userToCreateRequest.getNumberPhone())
                .email(userToCreateRequest.getEmail())
                .address(userToCreateRequest.getAddress())
                .build();
    }
}
