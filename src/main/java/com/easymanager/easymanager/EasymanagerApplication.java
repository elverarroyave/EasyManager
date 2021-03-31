package com.easymanager.easymanager;

import com.easymanager.easymanager.client.io.repository.ClientReposiroty;
import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.sale.io.repository.SaleRepository;
import com.easymanager.easymanager.sale.model.Sale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EasymanagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
			SpringApplication.run(EasymanagerApplication.class, args);

		/*
		//Beans
		SaleRepository saleRepository = configurableApplicationContext.getBean(SaleRepository.class);
		ClientReposiroty clientReposiroty = configurableApplicationContext.getBean(ClientReposiroty.class);

		//Relationship client and sale
		Client client1 = new Client(1516L, "Elver Arroyave");
		Sale sale = new Sale(256L,client1);
		Sale sale2 = new Sale(257L,client1);
		List<Sale> sales = Arrays.asList(sale,sale2);
		client1.setShopping(sales);
		clientReposiroty.save(client1);
		 */
	}

}
