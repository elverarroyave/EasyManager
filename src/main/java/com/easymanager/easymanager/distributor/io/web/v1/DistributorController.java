package com.easymanager.easymanager.distributor.io.web.v1;

import com.easymanager.easymanager.config.MessageResponse;
import com.easymanager.easymanager.distributor.io.web.v1.model.DistributorSaveRequest;
import com.easymanager.easymanager.distributor.io.web.v1.model.DistributorSaveResponse;
import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.distributor.service.DistributorService;
import com.easymanager.easymanager.distributor.service.model.DistributorSaveCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/api/v1/distributors")
@Api(tags = {"Distributors"}, value = "Distributors")
@CrossOrigin(origins = "http://localhost:4200")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @PostMapping
    @ApiOperation(value = "Save a distributor")
    public ResponseEntity<Void> save(@RequestBody @Valid DistributorSaveRequest distributorToCreate){

        DistributorSaveCmd distributorToCreateCmd = DistributorSaveRequest.fromModel(distributorToCreate);

        Distributor distributorCreated = distributorService.save(distributorToCreateCmd);

        URI location = fromUriString("/api/v1/distributors").path("/{id}")
                .buildAndExpand(distributorCreated.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find distributor by id")
    public ResponseEntity<DistributorSaveResponse> findById(@NotNull @PathVariable("id") Long id){

        Distributor distributorFound = distributorService.findById(id);

        return ResponseEntity.ok(DistributorSaveResponse.fromModel(distributorFound));

    }

    @GetMapping("/nit/{nit}")
    @ApiOperation(value = "Find distributor by nit")
    public ResponseEntity<DistributorSaveResponse> findByNit(@NotNull @RequestParam String nit){

        Distributor distributorFound = distributorService.findByNit(nit);

        return ResponseEntity.ok(DistributorSaveResponse.fromModel(distributorFound));
    }

    @GetMapping
    @ApiOperation(value = "Find all distributors")
    public ResponseEntity<List<DistributorSaveResponse>> findAll(){
        List<Distributor> distributorsFound = distributorService.findAll() ;
        return ResponseEntity.ok(DistributorSaveResponse.fromModelList(distributorsFound));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update distributor")
    public ResponseEntity<DistributorSaveResponse> update(@PathVariable("id") @NotNull Long id,
                                                          @RequestBody @Valid DistributorSaveRequest distributorToUpdate){

        DistributorSaveCmd distributorToSaveCmd = DistributorSaveRequest.fromModel(distributorToUpdate);

        Distributor distributorUpdated = distributorService.update(id, distributorToSaveCmd);

        return ResponseEntity.ok(DistributorSaveResponse.fromModel(distributorUpdated));

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete distributor")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") @NotNull Long id){

        distributorService.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Distributor deleted successfully."), HttpStatus.NO_CONTENT);

    }

}
