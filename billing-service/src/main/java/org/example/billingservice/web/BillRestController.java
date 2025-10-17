package org.example.billingservice.web;

import lombok.AllArgsConstructor;
import org.example.billingservice.entities.Bill;
import org.example.billingservice.feign.CustomerRestClient;
import org.example.billingservice.feign.ProductRestClient;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillRestController {
    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
