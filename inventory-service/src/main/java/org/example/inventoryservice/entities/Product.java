package org.example.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
}
