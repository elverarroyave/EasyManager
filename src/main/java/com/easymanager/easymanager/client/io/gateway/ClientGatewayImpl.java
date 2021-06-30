package com.easymanager.easymanager.client.io.gateway;

import com.easymanager.easymanager.client.io.repository.ClientReposiroty;
import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientGatewayImpl implements ClientGateway {

    @Autowired
    private ClientReposiroty clientReposiroty;

    @Override
    public Client save(@NotNull Client clientToCreate) {

        final Client clientToSave = clientToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        return clientReposiroty.save(clientToSave);
    }

    @Override
    public Client findById(@NotNull Long id) {

        Client clienFound = clientReposiroty.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Id not found, please check it id"));

        return clienFound;
    }

    @Override
    public List<Client> findAll() {
        return clientReposiroty.findAll();
    }

    @Override
    public Page<Client> findAllByPages(@NotNull Pageable pageable) {
        return clientReposiroty.findAll(pageable);
    }

    @Override
    public Client update(@NotNull Client clientToUpdate) {

        Client clientToBeUpdate = clientToUpdate.toBuilder()
                .updateDate(LocalDateTime.now())
                .build();

        return clientReposiroty.save(clientToBeUpdate);
    }

    @Override
    public void deleteById(@NotNull Long id) {
        findById(id);
        clientReposiroty.deleteById(id);
    }

    @Override
    public Client findByDocument(@NotNull String numDocument) {

        Client clientFound = clientReposiroty.findByDocument(numDocument)
                .orElseThrow(() -> new NotFoundExeption("Number document not found, please check."));

        return clientFound;
    }

    @Override
    public Optional<Client> verifyEmail(String email) {
        return clientReposiroty.findByEmail(email);
    }

    @Override
    public Optional<Client> verifyDocument(String document) {
        return clientReposiroty.findByDocument(document);
    }
}
