package com.easymanager.easymanager.client.service;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.model.ClientSaveCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientGateway clientGateway;

    @Autowired
    private ClientParameterValidartion clientParameterValidartion;

    @Override
    public Client save(@NotNull ClientSaveCmd clientToCreate) {

        Client clientToSave = ClientSaveCmd.toModel(clientToCreate);

        clientParameterValidartion.parametersValidation(clientToSave);

        return clientGateway.save(clientToSave);
    }

    @Override
    public List<Client> findAll() {
        return clientGateway.findAll();
    }

    @Override
    public Page<Client> findAllByPages(@NotNull Pageable pageable) {
        return clientGateway.findAllByPages(pageable);
    }

    @Override
    public Client findById(@NotNull Long id) {
        return clientGateway.findById(id);
    }

    @Override
    public Client update(@NotNull Long id, @NotNull ClientSaveCmd clientToUpdateCmd) {

        Client clientInDataBase = clientGateway.findById(id);

        Client clientToUpdate = clientInDataBase.toBuilder()
                .name(clientToUpdateCmd.getName())
                .lastName(clientToUpdateCmd.getLastName())
                .numDocument(clientToUpdateCmd.getNumDocument())
                .numPhone(clientToUpdateCmd.getNumPhone())
                .email(clientToUpdateCmd.getEmail())
                .address(clientToUpdateCmd.getAddress())
                .build();

        if(clientInDataBase.getId() != id) {
            clientParameterValidartion.parametersValidation(clientToUpdate);
        }

        return clientGateway.update(clientToUpdate);
    }

    @Override
    public void deleteById(@NotNull Long id) {
        clientGateway.deleteById(id);
    }

    @Override
    public Client findByNumDocument(@NotNull String numDocument) {
        return clientGateway.findByDocument(numDocument);
    }
}
