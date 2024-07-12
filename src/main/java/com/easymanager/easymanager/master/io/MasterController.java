package com.easymanager.easymanager.master.io;

import com.easymanager.easymanager.master.Service.MasterService;
import com.easymanager.easymanager.master.model.MasterBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master")
@Api(tags = {"Master"}, value = "Master")
@CrossOrigin(origins = "http://localhost:4200")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/{masterHeadName}")
    @ApiOperation(value = "Find all masters by name MasterHead")
    public List<MasterBody> findByMasterHeadName(@PathVariable String masterHeadName){
        return masterService.findByMasterHeadName(masterHeadName);
    }


}
