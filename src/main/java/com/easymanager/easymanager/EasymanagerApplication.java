package com.easymanager.easymanager;

import com.easymanager.easymanager.client.io.repository.ClientReposiroty;
import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.sale.io.repository.SaleRepository;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EasymanagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
			SpringApplication.run(EasymanagerApplication.class, args);


		//Beans
		SaleRepository saleRepository = configurableApplicationContext.getBean(SaleRepository.class);
		ClientReposiroty clientReposiroty = configurableApplicationContext.getBean(ClientReposiroty.class);
		ProductRepository productRepository = configurableApplicationContext.getBean(ProductRepository.class);

		//Relationship client and sale
		Client client1 = clientReposiroty.findById(419L)
				.orElseThrow(() -> new NotFoundExeption("Client not found"));

		//Products of shop
		Product product1 = productRepository.findById(1L)
				.orElseThrow(() -> new NotFoundExeption("Product not found"));

		Product product2 = productRepository.findById(33L)
				.orElseThrow(() -> new NotFoundExeption("Product not found"));

		Product product3 = productRepository.findById(65L)
				.orElseThrow(() -> new NotFoundExeption("Product not found"));

		//Sale
		List<SaleDetail> productsDetail = new ArrayList<>();
		Sale sale = new Sale(5015L, LocalDateTime.now(), LocalDateTime.now(),client1,productsDetail);

		//Create products detail
		SaleDetail product1Details = new SaleDetail(16561L,sale,product1,5);
		SaleDetail product2Details = new SaleDetail(16561L,sale,product2,10);
		SaleDetail product3Details = new SaleDetail(16561L,sale,product3,15);

		//Add products to sale
		productsDetail = Arrays.asList(product1Details,product2Details,product3Details);
		sale.setProductsDetail(productsDetail);

		//Persistence sale
		//saleRepository.save(sale);

		//Add sale to client
		List<Sale> sales = Arrays.asList(sale);
		client1.setShopping(sales);

		//Persistence client
		clientReposiroty.save(client1);

	}

}
