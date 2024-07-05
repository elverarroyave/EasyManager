package com.easymanager.easymanager.inventory.service;

import com.easymanager.easymanager.inventory.io.web.v1.model.InventorySaveRequest;
import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.model.InventorySaveCmd;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryGateway inventoryGateway;

    @Autowired
    private ProductService productService;

    @Override
    public Inventory findByProductId(Long productId) {
        return inventoryGateway.findByProductId(productId);
    }

    @Override
    public void create(InventorySaveCmd inventoryToCreateCmd) {
        //TODO validate product exist
        Product product = productService.findByCode(inventoryToCreateCmd.getProductCode());
        Inventory inventoryToCreate = InventorySaveCmd.fromModel(inventoryToCreateCmd);
        inventoryToCreate.setProduct(product);
        inventoryGateway.create(inventoryToCreate);
    }

    @Override
    public Page<Inventory> findAllByPages(Pageable pageable) {
        return inventoryGateway.findAllByPages(pageable);
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryGateway.findById(id);
    }
}
