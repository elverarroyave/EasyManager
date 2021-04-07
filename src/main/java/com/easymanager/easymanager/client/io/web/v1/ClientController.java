package com.easymanager.easymanager.client.io.web.v1;

import com.easymanager.easymanager.client.io.web.v1.model.ClientSaveRequest;
import com.easymanager.easymanager.client.io.web.v1.model.ClientSaveResponse;
import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientService;
import com.easymanager.easymanager.client.service.model.ClientSaveCmd;
import com.easymanager.easymanager.config.MessageResponse;
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
@RequestMapping("/api/v1/clients")
@Api(tags = {"Clients"}, value = "Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ApiOperation(value = "Save an client")
    public ResponseEntity<Void> save(@RequestBody @Valid ClientSaveRequest clientToCreate){

        ClientSaveCmd clientToSaveCmd = ClientSaveRequest.toModel(clientToCreate);

        Client clientCreated = clientService.save(clientToSaveCmd);

        //El estandar de los create dice no devolver un objeto si no la localizacion de este.
        URI location = fromUriString("/api/v1/clients").path("/{id}")
                .buildAndExpand(clientCreated.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping
    @ApiOperation(value = "Find all clients.")
    public List<ClientSaveResponse> findAll(){
        List<Client> clientsFound = clientService.findAll();
        return ClientSaveResponse.fromModelList(clientsFound);
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Find client by id.")
    public ResponseEntity<ClientSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        Client clientFound = clientService.findById(id);
        return ResponseEntity.ok(ClientSaveResponse.fromModel(clientFound));
    }

    @PostMapping("/findByDocument/{numDocument}")
    @ApiOperation(value = "Find client by number document.")
    public ResponseEntity<ClientSaveResponse> findByNumDocument(@Valid @PathVariable("numDocument") @NotNull String numDocument){
        Client clientFound = clientService.findByNumDocument(numDocument);
        return ResponseEntity.ok(ClientSaveResponse.fromModel(clientFound));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update and client")
    public ResponseEntity<ClientSaveResponse> update(@Valid @PathVariable("id") @NotNull Long id,
                                                     @NotNull @RequestBody ClientSaveRequest clientToUpdateRequest){

        ClientSaveCmd clientToUpdateCmd = ClientSaveRequest.toModel(clientToUpdateRequest);

        Client clientUpdated = clientService.update(id,clientToUpdateCmd);

        return ResponseEntity.ok(ClientSaveResponse.fromModel(clientUpdated));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an user by id.")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id){
        clientService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Client deleted successfully"), HttpStatus.NO_CONTENT);
    }

}
