package com.easymanager.easymanager.sale.io.web.v1;

import com.easymanager.easymanager.config.MessageResponse;
import com.easymanager.easymanager.sale.io.web.v1.model.SaleResponse;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.service.sale.SaleService;
import com.easymanager.easymanager.sale.service.sale.model.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/v1/sales")
@Api(tags = "Sales", value = "Sales")
@CrossOrigin(origins = "http://localhost:4200")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleService saleService;

    @PostMapping
    @ApiOperation(value = "Create a sale")
    public ResponseEntity<Void> create(@RequestBody @Valid List<Item> items, String numDocument){

        Sale saleCreate = saleService.create(numDocument, items);

        //El estandar de los create dice no devolver un objeto si no la localizacion de este.
        URI location = fromUriString("/api/v1/sales").path("/{id}")
                .buildAndExpand(saleCreate.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ApiOperation(value = "find sales by date range")
    public ResponseEntity<List<SaleResponse>> findByDateRange(@RequestParam String initDate,@RequestParam String finalDate){
        List<Sale> salesFound = saleService.findByDateRange(initDate, finalDate);
        return ResponseEntity.ok(SaleResponse.fromModelList(salesFound));
    }

    @GetMapping("/findByClient/{id}")
    @ApiOperation(value = "Find sales of client by id")
    public ResponseEntity<List<SaleResponse>> findSalesOfClient(@NotNull @PathVariable Long id){
        List <Sale> saleFound = saleService.findByClientId(id);
        return ResponseEntity.ok(SaleResponse.fromModelList(saleFound));
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Find sale by id")
    public ResponseEntity<SaleResponse> findById(@NotNull @PathVariable Long id){
        Sale saleFound = saleService.findById(id);
        return ResponseEntity.ok(SaleResponse.fromModel(saleFound));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a sale by id")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id){
        saleService.deleteById(id);
        return new ResponseEntity<>( new MessageResponse("Sale deleted successfully"), HttpStatus.NO_CONTENT);
    }
}
