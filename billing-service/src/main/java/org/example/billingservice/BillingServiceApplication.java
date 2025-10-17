package org.example.billingservice;

import org.example.billingservice.entities.Bill;
import org.example.billingservice.entities.Customer;
import org.example.billingservice.entities.Product;
import org.example.billingservice.entities.ProductItem;
import org.example.billingservice.feign.CustomerRestClient;
import org.example.billingservice.feign.ProductRestClient;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient
    ) {
        return args -> {
            Collection<Customer> customers = customerRestClient.findAllCustomers().getContent();
            Collection<Product> products = productRestClient.getAllProducts().getContent();
            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(LocalDateTime.now())
                        .customerId(customer.getId())
                        .build();
                billRepository.save(bill);
                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(1+new Random().nextInt(10))
                            .unitPrice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });
        };
    }
}
