package com.easymanager.easymanager.client.service;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.model.ClientSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ClientService {

    Client save(@NotNull ClientSaveCmd clientToCreate);

    List<Client> findAll();

    Page<Client> findAllByPages(@NotNull Pageable pageable);

    Client findById(@NotNull Long id);

    Client update(@NotNull Long id, @NotNull ClientSaveCmd clientToUpdate);

    void deleteById(@NotNull Long id);

    Client findByNumDocument(@NotNull String numDocument);

    List<Client> findByCoincidence(@NotNull String coincidence);
}
