package com.easymanager.easymanager.master.io.repository;

import com.easymanager.easymanager.master.model.MasterBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterBodyRepository extends JpaRepository<MasterBody, Long> {
    @Query("SELECT m FROM MasterBody m WHERE m.head.name = :masterHeadName")
    List<MasterBody> findByMasterHeadName(String masterHeadName);
}
