package com.easymanager.easymanager.order.service.order;

import com.easymanager.easymanager.order.model.Orden;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderGateway {

    Orden save(@NotNull Orden ordenToCreate);

    Orden update(@NotNull Orden ordenToUpdate);

    Orden findById(@NotNull Long id);

    List<Orden> findAll();

    void deleteById(@NotNull Long id);

}
