package org.example.billingservice.feign;

import org.example.billingservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/api/products/{productId}")
    Product getProductById(@PathVariable String productId);

    @GetMapping("/api/products")
    PagedModel<Product> getAllProducts();
}
