package com.easymanager.easymanager.client.io.web.v1.model;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.sale.model.Sale;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ClientSaveResponse {

    private String fullName;

    private String numDocument;

    private String numPhone;

    private String email;

    private String address;

    private List<Sale> shopping;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static ClientSaveResponse fromModel(Client clientToResponse){
        return ClientSaveResponse.builder()
                .fullName(clientToResponse.getFullName())
                .numDocument(clientToResponse.getNumDocument())
                .numPhone(clientToResponse.getNumPhone())
                .email(clientToResponse.getEmail())
                .address(clientToResponse.getAddress())
                .shopping(clientToResponse.getShopping())
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
