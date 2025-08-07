package com.example.orderservice.dto;

import com.example.orderservice.entity.CartItem;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private List<CartItem> cartItems;
    private Double totalAmount;
    private Double discount;
    private Instant createdAt;
}
