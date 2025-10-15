package org.example.customerservice;

import org.example.customerservice.entities.Customer;
import org.example.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("yassin")
                    .email("yassin@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("mohamed")
                    .email("mohamed@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("said")
                    .email("said@gmail.com")
                    .build());
        };
    }
}
