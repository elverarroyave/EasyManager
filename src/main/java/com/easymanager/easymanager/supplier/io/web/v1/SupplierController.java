package com.easymanager.easymanager.supplier.io.web.v1;

import com.easymanager.easymanager.config.MessageResponse;
import com.easymanager.easymanager.supplier.io.web.v1.model.SupplierSaveRequest;
import com.easymanager.easymanager.supplier.io.web.v1.model.SupplierSaveResponse;
import com.easymanager.easymanager.supplier.model.Supplier;
import com.easymanager.easymanager.supplier.service.SupplierService;
import com.easymanager.easymanager.supplier.service.model.SupplierSaveCmd;
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
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    @ApiOperation(value = "Save a distributor")
    public ResponseEntity<Void> save(@RequestBody @Valid SupplierSaveRequest distributorToCreate){

        SupplierSaveCmd supplierToCreateCmd = SupplierSaveRequest.fromModel(distributorToCreate);

        Supplier supplierCreated = supplierService.save(supplierToCreateCmd);

        URI location = fromUriString("/api/v1/distributors").path("/{id}")
                .buildAndExpand(supplierCreated.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find distributor by id")
    public ResponseEntity<SupplierSaveResponse> findById(@NotNull @PathVariable("id") Long id){

        Supplier supplierFound = supplierService.findById(id);

        return ResponseEntity.ok(SupplierSaveResponse.fromModel(supplierFound));

    }

    @GetMapping("/nit/{nit}")
    @ApiOperation(value = "Find distributor by nit")
    public ResponseEntity<SupplierSaveResponse> findByNit(@NotNull @RequestParam String nit){

        Supplier supplierFound = supplierService.findByNit(nit);

        return ResponseEntity.ok(SupplierSaveResponse.fromModel(supplierFound));
    }

    @GetMapping
    @ApiOperation(value = "Find all distributors")
    public ResponseEntity<List<SupplierSaveResponse>> findAll(){
        List<Supplier> distributorsFound = supplierService.findAll() ;
        return ResponseEntity.ok(SupplierSaveResponse.fromModelList(distributorsFound));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update distributor")
    public ResponseEntity<SupplierSaveResponse> update(@PathVariable("id") @NotNull Long id,
                                                       @RequestBody @Valid SupplierSaveRequest distributorToUpdate){

        SupplierSaveCmd distributorToSaveCmd = SupplierSaveRequest.fromModel(distributorToUpdate);

        Supplier supplierUpdated = supplierService.update(id, distributorToSaveCmd);

        return ResponseEntity.ok(SupplierSaveResponse.fromModel(supplierUpdated));

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete distributor")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") @NotNull Long id){

        supplierService.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Supplier deleted successfully."), HttpStatus.NO_CONTENT);

    }

}
