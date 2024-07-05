package com.easymanager.easymanager.inventory.io.web.v1;

import com.easymanager.easymanager.inventory.io.web.v1.model.InventorySaveRequest;
import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.InventoryService;
import com.easymanager.easymanager.inventory.service.model.InventorySaveCmd;
import com.easymanager.easymanager.product.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/inventory")
@Api(tags = "Inventory", value = "Inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping
    public void create(@RequestBody InventorySaveRequest inventoryToCreateSaveRequest) {
        InventorySaveCmd inventoryToCreateCmd = InventorySaveRequest.fromModel(inventoryToCreateSaveRequest);
        inventoryService.create(inventoryToCreateCmd);
    }

    @GetMapping("inventoryByPages")
    @ApiOperation(value = "Get all inventory by pages.")
    public ResponseEntity<Page<Inventory>> getAllInventoryByPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "false") boolean asc
    ) {
        Page<Inventory> inventoryServiceAllByPages;
        if (asc) {
            inventoryServiceAllByPages = inventoryService.findAllByPages(
                    PageRequest.of(page, size, Sort.by(order).descending()));
        } else {
            inventoryServiceAllByPages = inventoryService.findAllByPages(
                    PageRequest.of(page, size, Sort.by(order).ascending()));
        }
        return new ResponseEntity<Page<Inventory>>(inventoryServiceAllByPages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a inventory by id.")
    public ResponseEntity<Inventory> findById(@PathVariable @NotNull  Long id) {
        Inventory inventoryFound = inventoryService.findById(id);
        return new ResponseEntity<Inventory>(inventoryFound, HttpStatus.OK);
    }

}
