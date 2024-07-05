package com.easymanager.easymanager.inventory.io.gateway;

import com.easymanager.easymanager.inventory.io.repository.InventoryRepository;
import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.InventoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryGatewayImp implements InventoryGateway {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    public Inventory findByProductCode(String productCode) {
        return inventoryRepository.findByProductCode(productCode);
    }

    @Override
    public void create(Inventory inventoryToCreate) {
        inventoryRepository.save(inventoryToCreate);
    }

    @Override
    public Page<Inventory> findAllByPages(Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }
}
