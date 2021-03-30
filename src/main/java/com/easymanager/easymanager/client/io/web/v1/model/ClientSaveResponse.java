package com.easymanager.easymanager.client.io.web.v1.model;

import com.easymanager.easymanager.client.model.Client;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClientSaveResponse {

    private String fullName;

    private String numDocument;

    private String numPhone;

    private String email;

    private String address;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static ClientSaveResponse fromModel(Client clientToResponse){
        return ClientSaveResponse.builder()
                .fullName(clientToResponse.getFullName())
                .numDocument(clientToResponse.getNumDocument())
                .numPhone(clientToResponse.getNumPhone())
                .email(clientToResponse.getEmail())
                .address(clientToResponse.getAddress())
                .createDate(clientToResponse.getCreateDate())
                .updateDate(clientToResponse.getUpdateDate())
                .build();
    }

}
