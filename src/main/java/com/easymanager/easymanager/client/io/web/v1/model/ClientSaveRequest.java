package com.easymanager.easymanager.client.io.web.v1.model;

import com.easymanager.easymanager.client.service.model.ClientSaveCmd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientSaveRequest {

    private String fullName;

    private String numDocument;

    private String numPhone;

    private String email;

    private String address;

    public static ClientSaveCmd toModel(ClientSaveRequest clientToSaveRequest){
        return ClientSaveCmd.builder()
                .fullName(clientToSaveRequest.getFullName())
                .numDocument(clientToSaveRequest.getNumDocument())
                .numPhone(clientToSaveRequest.getNumPhone())
                .email(clientToSaveRequest.getEmail())
                .address(clientToSaveRequest.getAddress())
                .build();
    }

}
