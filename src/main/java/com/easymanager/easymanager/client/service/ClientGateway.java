package com.easymanager.easymanager.client.service;

import com.easymanager.easymanager.client.model.Client;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ClientGateway {

    Client save(@NotNull Client clientToCreate);

    Client findById(@NotNull Long id);

    List<Client> findAll();

    Client update(@NotNull Client clientToUpdate);

    void deleteById(@NotNull Long id);

    Client findByDocument(@NotNull String numDocument);

}
