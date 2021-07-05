package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.sale.model.Sale;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleGateway {

    Sale save(@NotNull Sale saleToCreate);

    List<Sale> findAll();

    Sale findById(@NotNull Long id);

    Sale update(@NotNull Sale saleToUpdate);

    void deleteById(@NotNull Long id);

    List<Sale> findByClientId(@NotNull Client client);

    List<Sale> findByDateRange(LocalDateTime initDate, LocalDateTime finalDate);
}
