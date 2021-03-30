package com.easymanager.easymanager.client.io.repository;

import com.easymanager.easymanager.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientReposiroty extends JpaRepository<Client,Long> {

}
