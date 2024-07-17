package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.inventory.service.InventoryGateway;
import com.easymanager.easymanager.master.Service.MasterService;
import com.easymanager.easymanager.master.io.repository.MasterBodyRepository;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.role.service.RoleGateway;
import com.easymanager.easymanager.sale.io.web.v1.model.SaleSaveRequest;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.saleDetails.SaleDetailsGateway;
import com.easymanager.easymanager.sale.service.saleDetails.TransactionDetail;
import com.easymanager.easymanager.tools.Dates;
import com.easymanager.easymanager.user.UserValidations;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService{
    
    @Autowired
    private SaleGateway saleGateway;

    @Autowired
    private ClientGateway clientGateway;

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private SaleDetailsGateway saleDetailsGateway;

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private RoleGateway roleGateway;

    @Autowired
    private InventoryGateway inventoryGateway;

    @Autowired
    private MasterBodyRepository masterBodyRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Sale create(@NotNull SaleSaveRequest saleSaveRequest) {

        float currentMonthlyInterest = Float.parseFloat(masterBodyRepository.findByMasterHeadName("CURRENT_MONTHLY_INTEREST").get(0).getValue());

        TransactionDetail saleDetails = new TransactionDetail();
        //TODO se debe crear funcionalidad para recuperar el usuario que realiza la venta
        User userSeller = UserValidations.verifyUserMakeToSale(999L, userGateway, roleGateway);
        List<SaleDetail> productsDetail = saleDetails.processProductList(saleSaveRequest, productGateway, inventoryGateway, currentMonthlyInterest);
        Sale saleCreated = saleDetails.buildSale(saleSaveRequest, userSeller, productsDetail, currentMonthlyInterest, clientGateway, saleGateway);
        productsDetail.forEach(productDetail -> productDetail.setSale(saleCreated));
        saleDetailsGateway.save(productsDetail);
        //TODO se debe crear funcionalidad para crear factura de la venta
        //TODO se debe crear funcionalidad para enviar un correo al cliente con la factura de la venta
        return saleCreated;
    }

    public List<Sale> findByDateRange(@NotNull String initDate, String finalDate){
        logger.debug("Sale Service, begin find sales by date range init = {} - end = {}", initDate, finalDate);
        logger.debug("Sale Service, end find sales by date range init = {} - end = {}", initDate, finalDate);
        List<Sale> sales = saleGateway.findByDateRange(
                Dates.convertDateStringToLocalDateTime(initDate)
                , Dates.convertDateStringToLocalDateTime(finalDate));
        if(sales.isEmpty()){
            throw new NotFoundExeption("No se encontraron ventas en el rango de fechas seleccionadas.");
        }
        return sales;
    }

    @Override
    public Sale findById(@NotNull long id) {
        logger.debug("Sale service, begin find sale by id = {}", id);
        Sale saleFound = saleGateway.findById(id);
        logger.debug("Sale service, end find sale by id = {}", id);
        return saleFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Sale Service, Begin delete sale by id = {}", id);
        Sale saleToDelete = saleGateway.findById(id);
        saleDetailsGateway.deleteSalesDetails(saleToDelete);
        saleGateway.deleteById(id);
        logger.debug("Sale Service, End delete sale by id = {}", id);
    }

    @Override
    public List<Sale> findByClientId(@NotNull Long id) {
        logger.debug("Sale Service, Begin find sale by client Id = {}", id);
        Client clientFound = clientGateway.findById(id);
        List<Sale> salesFound = saleGateway.findByClientId(clientFound);
        logger.debug("Sale Service, End find sale by client Id = {}", id);
        return salesFound;
    }

}
