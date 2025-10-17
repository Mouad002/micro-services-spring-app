package org.example.billingservice.feign;

import org.example.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{customerId}")
    Customer findCustomerById(@PathVariable Long customerId);

    @GetMapping("/api/customers")
    PagedModel<Customer> findAllCustomers();
}
