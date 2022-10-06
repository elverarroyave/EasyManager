package com.easymanager.easymanager.order.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.order.io.repository.OrderRepository;
import com.easymanager.easymanager.order.model.Orden;
import com.easymanager.easymanager.order.service.order.OrderGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderGatewayImpl implements OrderGateway {

    @Autowired
    OrderRepository orderRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Orden save(@NotNull Orden ordenToCreate) {

        logger.debug("Begin save order = {}", ordenToCreate);

        final Orden ordenToBeCreate = ordenToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Orden ordenSave = orderRepository.save(ordenToBeCreate);

        logger.debug("End save order = {}", ordenSave);

        return ordenSave;
    }

    @Override
    public Orden update(@NotNull Orden ordenToUpdate) {

        logger.debug("Begin update order = {}", ordenToUpdate);

        final Orden ordenToBeUpdate = ordenToUpdate.toBuilder()
                .updateDate(LocalDateTime.now())
                .build();

        Orden updatedOrden = orderRepository.save(ordenToBeUpdate);

        logger.debug("End update order {}", ordenToBeUpdate);

        return updatedOrden;
    }

    @Override
    public Orden findById(@NotNull Long id) {

        logger.debug("Being find a order by id {}", id);

        Orden ordenFound = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Orden con id: "+id+", no encontrada. Por favor revisar."));

        logger.debug("End find a order by id, order = {}", ordenFound);

        return ordenFound;
    }

    @Override
    public List<Orden> findAll() {

        logger.debug("Begin find all ordens");

        List<Orden> ordens = orderRepository.findAll();

        logger.debug("End find all ordens = {}", ordens);

        return ordens;
    }

    @Override
    public void deleteById(@NotNull Long id) {

        logger.debug("Begin delete order by id = {}", id);

        orderRepository.deleteById(id);

        logger.debug("Order deleted");
    }
}
