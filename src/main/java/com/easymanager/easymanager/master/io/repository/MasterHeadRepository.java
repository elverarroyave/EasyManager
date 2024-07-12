package com.easymanager.easymanager.master.io.repository;

import com.easymanager.easymanager.master.model.MasterHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterHeadRepository extends JpaRepository<MasterHead, Long> {
    MasterHead findByName(String name);
}
