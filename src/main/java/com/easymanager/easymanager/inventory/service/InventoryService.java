package com.easymanager.easymanager.inventory.service;

import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.model.InventorySaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface InventoryService {
    Inventory findByProductId(@NotNull  Long productId);

    void create(InventorySaveCmd inventoryToCreateCmd);

    Page<Inventory> findAllByPages(@NotNull Pageable pageable);

    Inventory findById(Long id);
}
