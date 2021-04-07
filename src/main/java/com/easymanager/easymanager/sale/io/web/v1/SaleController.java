package com.easymanager.easymanager.sale.io.web.v1;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.service.SaleService;
import com.easymanager.easymanager.sale.service.model.ItemDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/api/v1/sales")
@Api(tags = "Sales", value = "Sales")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleService saleService;

    @PostMapping
    @ApiOperation(value = "Create a sale")
    public ResponseEntity<Void> create(@RequestBody @Valid List<ItemDetail> itemDetails, String idClient){

        Sale saleCreate = saleService.create(idClient, itemDetails);

        //El estandar de los create dice no devolver un objeto si no la localizacion de este.
        URI location = fromUriString("/api/v1/sales").path("/{id}")
                .buildAndExpand(saleCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
