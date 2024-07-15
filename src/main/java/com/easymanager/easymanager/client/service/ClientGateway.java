package com.easymanager.easymanager.client.service;

import com.easymanager.easymanager.client.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ClientGateway {

    Client save(@NotNull Client clientToCreate);

    Client findById(@NotNull Long id);

    List<Client> findAll();

    Page<Client> findAllByPages(@NotNull Pageable pageable);

    Client update(@NotNull Client clientToUpdate);

    void deleteById(@NotNull Long id);

    Client findByDocument(@NotNull String numDocument);

    Optional<Client> verifyEmail(String email);

    Optional<Client> verifyDocument(String document);

    List<Client> findByCoincidence(@NotNull String coincidence);

}
