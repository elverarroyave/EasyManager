package com.easymanager.easymanager.master.Service;

import com.easymanager.easymanager.master.io.repository.MasterBodyRepository;
import com.easymanager.easymanager.master.model.MasterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService{

    @Autowired
    private MasterBodyRepository masterBodyRespository;


    @Override
    public List<MasterBody> findByMasterHeadName(String masterHeadName) {
        return masterBodyRespository.findByMasterHeadName(masterHeadName);
    }
}
