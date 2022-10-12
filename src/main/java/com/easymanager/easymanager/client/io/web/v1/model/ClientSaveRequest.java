package com.easymanager.easymanager.client.io.web.v1.model;

import com.easymanager.easymanager.client.service.model.ClientSaveCmd;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class ClientSaveRequest {
    @Size(min = 3, max = 100, message = "El nombre debe tener un tamaño entre 3 y 50 caracteres.")
    private String name;

    @Size(min = 3, max = 100, message = "El apellido debe tener un tamaño entre 3 y 50 caracteres.")
    private String lastName;

    @Size(min = 3, max = 20, message = "El documento debe tener un tamaño entre 3 y 20 caracteres.")
    private String numDocument;

    @Size(min = 3, max = 20, message = "El teléfono debe tener un tamaño entre 3 y 20 caracteres.")
    private String numPhone;

    @Size(min = 3, max = 100, message = "El email debe tener un tamaño entre 3 y 100 caracteres.")
    private String email;

    @Size(min = 3, max = 100, message = "La dirección debe tener un tamaño entre 3 y 100 caracteres.")
    private String address;

    public static ClientSaveCmd toModel(ClientSaveRequest clientToSaveRequest){
        return ClientSaveCmd.builder()
                .name(clientToSaveRequest.getName())
                .lastName(clientToSaveRequest.getLastName())
                .numDocument(clientToSaveRequest.getNumDocument())
                .numPhone(clientToSaveRequest.getNumPhone())
                .email(clientToSaveRequest.getEmail())
                .address(clientToSaveRequest.getAddress())
                .build();
    }

}
