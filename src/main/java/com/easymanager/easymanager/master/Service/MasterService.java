package com.easymanager.easymanager.master.Service;

import com.easymanager.easymanager.master.model.MasterBody;

import java.util.List;

public interface MasterService {

    List<MasterBody> findByMasterHeadName(String masterHeadName);

}
