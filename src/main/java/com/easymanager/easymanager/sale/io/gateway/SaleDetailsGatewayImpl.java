package com.easymanager.easymanager.sale.io.gateway;

import com.easymanager.easymanager.sale.io.repository.SaleDetailRepository;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.saleDetails.SaleDetailsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class SaleDetailsGatewayImpl implements SaleDetailsGateway {

    @Autowired
    SaleDetailRepository saleDetailRepository;

    @Override
    public List<SaleDetail> save(@NotNull List<SaleDetail> saleDetail) {
        return saleDetailRepository.saveAll(saleDetail);
    }

    @Override
    public void deleteSalesDetails(@NotNull Sale sale) {
        saleDetailRepository.deleteSalesDetails(sale);
    }
}
