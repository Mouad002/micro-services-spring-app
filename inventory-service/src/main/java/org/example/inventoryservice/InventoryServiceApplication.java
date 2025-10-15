package org.example.inventoryservice;

import org.example.inventoryservice.entities.Product;
import org.example.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Laptop")
                    .price(1000.0)
                    .quantity(299)
                    .build());

            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Smartphone")
                    .price(500.0)
                    .quantity(450)
                    .build());

            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Headphones")
                    .price(150.0)
                    .quantity(120)
                    .build());

            productRepository.findAll().forEach(System.out::println);
        };
    }
}
