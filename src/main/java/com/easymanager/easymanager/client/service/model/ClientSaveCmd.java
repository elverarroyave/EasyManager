package com.easymanager.easymanager.client.service.model;

import com.easymanager.easymanager.client.model.Client;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ClientSaveCmd {

    @NotNull(message = "Your name is required")
    @NotBlank(message = "Your name cannot be blank")
    private String fullName;

    @NotNull(message = "Your number document is required")
    @NotBlank(message = "Your number document cannot be blank")
    private String numDocument;

    @NotNull(message = "Your number phone is required")
    @NotBlank(message = "Your number phone cannot be blank")
    private String numPhone;

    @Email
    @NotNull(message = "Your email is required")
    @NotBlank(message = "Your email cannot be blank")
    private String email;

    @NotNull(message = "Your address is required")
    @NotBlank(message = "Your address cannot be blank")
    private String address;


    public static Client toModel(ClientSaveCmd clientToSaveCmd){
        return Client.builder()
                .fullName(clientToSaveCmd.getFullName())
                .numDocument(clientToSaveCmd.getNumDocument())
                .numPhone(clientToSaveCmd.getNumPhone())
                .email(clientToSaveCmd.getEmail())
                .address(clientToSaveCmd.getAddress())
                .build();
    }

}
