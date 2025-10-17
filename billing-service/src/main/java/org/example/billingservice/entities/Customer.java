package org.example.billingservice.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
    private Long id;
    private String name;
    private String email;
}
