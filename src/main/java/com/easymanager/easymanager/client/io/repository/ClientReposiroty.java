package com.easymanager.easymanager.client.io.repository;

import com.easymanager.easymanager.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientReposiroty extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM CLIENT c WHERE c.numDocument = ?1")
    Optional<Client> findByDocument(String numDocument);

}
