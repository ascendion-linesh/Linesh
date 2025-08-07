package com.example.orderservice.dto;

import com.example.orderservice.entity.CartItem;
import lombok.Data;
import java.util.List;

@Data
public class RewardsRequest {
    private Long userId;
    private List<CartItem> cartItems;
}
