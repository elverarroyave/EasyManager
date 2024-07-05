package com.easymanager.easymanager.inventory.service;

import com.easymanager.easymanager.inventory.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface InventoryGateway {
    Inventory findByProductId(@NotNull Long productId);

    Inventory findByProductCode(@NotNull String productCode);

    void create(Inventory inventoryToCreate);

    Page<Inventory> findAllByPages(@NotNull Pageable pageable);

    Inventory findById(Long id);
}
