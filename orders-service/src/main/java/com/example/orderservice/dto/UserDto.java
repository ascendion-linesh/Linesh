package com.example.orderservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int totalOrders;
    private double totalSpent;
}
