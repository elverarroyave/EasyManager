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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clients")
@Api(tags = {"Clients"}, value = "Clients")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/clientsByPages")
    @ApiOperation(value = "Show all clients by pages.")
    public ResponseEntity<Page<Client>> findAllByPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "false") boolean asc
    ){
        Page<Client> clientsFound = clientService.findAllByPages(
                PageRequest.of(page,size, Sort.by(order).descending())
        );
        if (!asc)
            clientsFound = clientService.findAllByPages(
                    PageRequest.of(page,size, Sort.by(order).ascending())
            );

        clientsFound.forEach(client -> {
            client.add(linkTo(methodOn(ClientController.class).findById(client.getId())).withSelfRel());
        });

        return new ResponseEntity<Page<Client>>(clientsFound, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find client by id.")
    public ResponseEntity<CollectionModel<ClientSaveResponse>> findById(@Valid @PathVariable("id") @NotNull Long id){
        Client clientFound = clientService.findById(id);

        ClientSaveResponse clientSaveResponse = ClientSaveResponse.fromModel(clientFound);

        clientSaveResponse.add(linkTo(methodOn(ClientController.class).findById(clientFound.getId())).withSelfRel());

        Link allClientLink = linkTo(methodOn(ClientController.class).findAll()).withSelfRel();

        List<ClientSaveResponse> clients = new ArrayList<>();

        clients.add(clientSaveResponse);

        return ResponseEntity.ok(CollectionModel.of(clients, allClientLink));
    }

    @GetMapping("/document/{numDocument}")
    @ApiOperation(value = "Find client by number document.")
    public ResponseEntity<CollectionModel<ClientSaveResponse>> findByNumDocument(@Valid @PathVariable("numDocument") @NotNull String numDocument){
        Client clientFound = clientService.findByNumDocument(numDocument);

        ClientSaveResponse clientSaveResponse = ClientSaveResponse.fromModel(clientFound);

        clientSaveResponse.add(linkTo(methodOn(ClientController.class)
                .findByNumDocument(clientSaveResponse.getNumDocument())).withSelfRel());

        Link allClientLink = linkTo(methodOn(ClientController.class).findAll()).withSelfRel();

        List<ClientSaveResponse> clients = new ArrayList<>();

        clients.add(clientSaveResponse);

        return ResponseEntity.ok(CollectionModel.of(clients, allClientLink));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update and client")
    public ResponseEntity<ClientSaveResponse> update(@PathVariable("id") @NotNull Long id,
                                                     @RequestBody @Valid ClientSaveRequest clientToUpdateRequest){

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
