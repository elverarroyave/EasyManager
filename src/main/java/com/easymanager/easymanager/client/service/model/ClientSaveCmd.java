package com.easymanager.easymanager.client.service.model;

import com.easymanager.easymanager.client.model.Client;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ClientSaveCmd {

    @NotNull(message = "Your name is required")
    @NotBlank(message = "Your name cannot be blank")
    private String name;

    @NotNull(message = "Your lastName is required")
    @NotBlank(message = "Your lastName cannot be blank")
    private String lastName;

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
                .firstName(clientToSaveCmd.getName())
                .lastName(clientToSaveCmd.getLastName())
                .numDocument(clientToSaveCmd.getNumDocument())
                .numberPhone(clientToSaveCmd.getNumPhone())
                .email(clientToSaveCmd.getEmail())
                .address(clientToSaveCmd.getAddress())
                .build();
    }

}
