package com.easymanager.easymanager.client.io.web.v1.model;

import com.easymanager.easymanager.client.model.Client;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ClientSaveResponse extends RepresentationModel<ClientSaveResponse> {

    private Long id;

    private String name;

    private String lastName;

    private String numDocument;

    private String numPhone;

    private String email;

    private String address;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static ClientSaveResponse fromModel(Client clientToResponse){
        return ClientSaveResponse.builder()
                .id(clientToResponse.getId())
                .name(clientToResponse.getFirstName())
                .lastName(clientToResponse.getLastName())
                .numDocument(clientToResponse.getNumDocument())
                .numPhone(clientToResponse.getNumberPhone())
                .email(clientToResponse.getEmail())
                .address(clientToResponse.getAddress())
                .createDate(clientToResponse.getCreateDate())
                .updateDate(clientToResponse.getUpdateDate())
                .build();
    }

    public static List<ClientSaveResponse> fromModelList(List<Client> clientsToResponse){
        List<ClientSaveResponse> clientsSaveResponse = new ArrayList<>();

        for (Client clientToResponse : clientsToResponse) {
            clientsSaveResponse.add(ClientSaveResponse.fromModel(clientToResponse));
        }
        return  clientsSaveResponse;
    }

}
